package com.chattingo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chattingo.Model.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("select m from Message m join m.chat c where c.id=:chatId")
    public List<Message> findByChatId(@Param("chatId") Integer chatId);

    @Query("select m from Message m where m.chat.id=:chatId and m.user.id<>:userId and m.seen=false")
    public List<Message> findUnseenMessages(@Param("chatId") Integer chatId, @Param("userId") Integer userId);
}
