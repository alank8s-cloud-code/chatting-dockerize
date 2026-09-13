import React from 'react';
import { BsCheck, BsCheckAll } from 'react-icons/bs';

const MessageCard = ({ isReqUserMessage, content, timestamp, profilePic, seen }) => {
  // isReqUserMessage is true when the message is from the OTHER person.
  // Ticks only make sense on messages that I (the current user) sent.
  const isMyOwnMessage = !isReqUserMessage;

  return (
    <div className={`flex w-full ${isReqUserMessage ? 'justify-start' : 'justify-end'} my-2`}>
      {!isReqUserMessage && profilePic && (
        <img src={profilePic} alt="profile" className="w-8 h-8 rounded-full mr-2" />
      )}
      <div
        className={`max-w-xs px-4 py-2 rounded-lg ${
          isReqUserMessage ? 'bg-blue-500 text-white' : 'bg-gray-200 text-black'
        }`}
      >
        <p>{content}</p>
        <div className="flex items-center justify-end gap-1 mt-1">
          {timestamp && (
            <span className="text-xs text-gray-600">
              {new Date(timestamp).toLocaleTimeString()}
            </span>
          )}
          {isMyOwnMessage && (
            seen ? (
              <BsCheckAll className="text-blue-300" title="Seen" />
            ) : (
              <BsCheck className="text-gray-300" title="Sent" />
            )
          )}
        </div>
      </div>
    </div>
  );
};

export default MessageCard;
