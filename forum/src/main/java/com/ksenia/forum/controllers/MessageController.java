package com.ksenia.forum.controllers;

import com.ksenia.forum.models.Message;
import com.ksenia.forum.services.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(path = "/v1/api/forum/messages")
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public List<Message> getAllMessages(){
        return messageService.getAllMessages();
    }

    @GetMapping(path="/email/{email}")
    public List<Message> getAllMessagesByEmail(@PathVariable("email") String email){
        return this.messageService.getAllMessagesByEmail(email);
    }

    @GetMapping(path="/topic/{topic}")
    public List<Message> getAllMessagesByTopic(@PathVariable("topic") String topic){
        return this.messageService.getAllMessagesByTopic(topic);
    }

    @GetMapping(path="/{id}")
    public Optional<Message> getMessageById(@PathVariable("id") Long id){
        return messageService.getMessageById(id);
    }


    @PostMapping
    public void createNewMessage(@RequestBody Message message){
        messageService.createNewMessage(message);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMessage(@PathVariable("id") Long id){
        messageService.deleteMessageById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
