package com.chattingo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${cors.allowed.origins:http://localhost:3000,http://localhost}")
    private String allowedOrigins;

    @SuppressWarnings("null")
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Parse allowed origins from environment variable
        String[] origins = allowedOrigins.split(",");
        registry.addEndpoint("/ws").setAllowedOrigins(origins).withSockJS();
    }

    @SuppressWarnings("null")
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        // "/direct" carries one-on-one chat broadcasts (a plain topic per chat id,
        // e.g. "/direct/{chatId}"). We intentionally do NOT reuse "/user" for this:
        // Spring reserves that prefix for its own per-principal user-destination
        // feature (setUserDestinationPrefix / convertAndSendToUser), which expects
        // "/user/queue/..." style destinations tied to an authenticated Principal.
        // Using "/user/{chatId}" as a plain broadcast topic collided with that
        // feature and could cause subscriptions to be silently rewritten.
        //
        // "/notify" is a per-USER (not per-chat) channel, e.g. "/notify/{userId}".
        // A client can only subscribe to per-chat channels for chats it already
        // knows about - a brand new chat someone just started with you has no
        // existing subscription anywhere. This channel tells a user's client
        // "your chat list changed, go refresh it", which is how a newly created
        // conversation shows up live instead of only after a page refresh.
        registry.enableSimpleBroker("/group", "/direct", "/notify");
    }

}

