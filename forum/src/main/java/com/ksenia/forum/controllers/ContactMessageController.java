package com.ksenia.forum.controllers;

import com.ksenia.forum.models.ContactMessage;
import com.ksenia.forum.services.ContactMessageService;
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
@RequestMapping(path = "/v1/api/contact-messages")
public class ContactMessageController {

    private final ContactMessageService messageService;

    @GetMapping
    public List<ContactMessage> getAllMessages(){
        return messageService.getAllMessages();
    }

    @GetMapping(path="/{id}")
    public Optional<ContactMessage> getMessageById(@PathVariable("id") Long id){
        return messageService.getMessageById(id);
    }


    @PostMapping
    public void createNewMessage(@RequestBody ContactMessage message){
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
