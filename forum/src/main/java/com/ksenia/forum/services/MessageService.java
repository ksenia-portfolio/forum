package com.ksenia.forum.services;

import com.ksenia.forum.models.Message;
import com.ksenia.forum.repositories.AccountRepository;
import com.ksenia.forum.repositories.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public List<Message> getAllMessages(){
        return this.messageRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Transactional
    public List<Message> getAllMessagesByEmail(String email){
        return this.messageRepository.findAllByEmail(email);
    }

    @Transactional
    public List<Message> getAllMessagesByTopic(String topic){
        return this.messageRepository.findAllByTopic(topic);
    }

    public Optional<Message> getMessageById(Long id){
        if(!messageRepository.existsById(id)){
            throw new IllegalStateException("Message with id " + id + " does not exist.");
        } return messageRepository.findById(id);
    }

    public void createNewMessage(Message message){
        message.setDateCreated(LocalDateTime.now());
        message.setTopic(message.getTopic());
        message.setAnswered(false);
        messageRepository.save(message);
    }


    public void deleteMessageById(Long id){
        if(!messageRepository.existsById(id)){
            throw new IllegalStateException("Message with id " + id + " does not exist.");
        }messageRepository.deleteById(id);
    }
}
