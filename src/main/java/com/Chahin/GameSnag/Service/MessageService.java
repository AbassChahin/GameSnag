package com.Chahin.GameSnag.Service;
import com.Chahin.GameSnag.Entities.Message;
import com.Chahin.GameSnag.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageService {

    @Autowired
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message addMessage(Message message) {
        Message messageToAdd = checkMessageValid(message);

        if (messageToAdd == null) {
            return null;
        }

        return messageRepository.save(message);
    }

    public Message getMessageByName(String name) {
        return messageRepository.findByName(name);
    }

    public Message getMessageByEmail(String email) {
        return messageRepository.findByEmail(email);
    }

    public void deleteMessageByName(String name) {
        messageRepository.deleteByName(name);
    }

    public void deleteMessageByEmail(String email) {
        messageRepository.deleteByEmail(email);
    }


    // helper method to check valid request body
    public Message checkMessageValid(Message message) {
        // Ensure not null
        if (message == null) {
            return null;
        }

        // Name check
        if (message.getName() == null || message.getName().isEmpty()) {
            return null;
        }

        // Email check
        if (message.getEmail() == null || message.getEmail().isEmpty()) {
            return null;
        }

        // Message check
        if (message.getMessage() == null || message.getMessage().isEmpty()) {
            return null;
        }

        return message;
    }
}
