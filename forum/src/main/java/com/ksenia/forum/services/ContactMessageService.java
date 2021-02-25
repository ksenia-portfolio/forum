package com.ksenia.forum.services;

import com.ksenia.forum.models.ContactMessage;
import com.ksenia.forum.models.Message;
import com.ksenia.forum.repositories.ContactMessageRepository;
import com.ksenia.forum.repositories.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ContactMessageService {

    private final ContactMessageRepository messageRepository;


    public List<ContactMessage> getAllMessages(){
        return this.messageRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Optional<ContactMessage> getMessageById(Long id){
        if(!messageRepository.existsById(id)){
            throw new IllegalStateException("Message with id " + id + " does not exist.");
        } return messageRepository.findById(id);
    }

    public void createNewMessage(ContactMessage message){
        messageRepository.saveAndFlush(message);
    }


    public void deleteMessageById(Long id){
        if(!messageRepository.existsById(id)){
            throw new IllegalStateException("Message with id " + id + " does not exist.");
        }messageRepository.deleteById(id);
    }
}
