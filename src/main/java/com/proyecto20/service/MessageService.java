package com.proyecto20.service;

import com.proyecto20.model.Message;
import com.proyecto20.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message create(Message message) {
        if (message.getIdMessage() == null){
            return messageRepository.save(message);
        }else{
            Optional<Message> messageNew = getMessage(message.getIdMessage());
            if (messageNew.isEmpty()){
                return messageRepository.save(message);
            }else {
                return message;
            }
        }
    }

    public Optional<Message> getMessage(Integer id) {
        return messageRepository.findById(id);
    }

    public List<Message> messages() {
        return (List<Message>) messageRepository.findAll();
    }

    public Message update(Message message) {
        if (message != null && message.getIdMessage() != null){
            if (messageRepository.existsById(message.getIdMessage())){
                Optional<Message> oldMessage = messageRepository.findById(message.getIdMessage());
                Message editedMessage = oldMessage.get();
                if (message.getMessageText() != null){
                    editedMessage.setMessageText(message.getMessageText());
                }
                if (message.getLib() != null){
                    editedMessage.setLib(message.getLib());
                }
                if (message.getClient() != null){
                    editedMessage.setClient(message.getClient());
                }
                return messageRepository.save(editedMessage);
            }else{
                return message;
            }
        }else {
            return message;
        }
    }

    public boolean delete(Integer id) {
        if(messageRepository.existsById(id)){
            messageRepository.deleteById(id);
            return true;
        }else
            return false;
    }
}
