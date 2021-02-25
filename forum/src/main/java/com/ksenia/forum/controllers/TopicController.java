package com.ksenia.forum.controllers;

import com.ksenia.forum.models.Topic;
import com.ksenia.forum.services.TopicService;
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
@RequestMapping(path = "/v1/api/forum/topics")
public class TopicController {

    private final TopicService topicService;

    @GetMapping
    public List<Topic> getAllTopics(){
        return topicService.getAllTopics();
    }

    @GetMapping(path="/{id}")
    public Optional<Topic> getTopicById(@PathVariable("id") Long id){
        return topicService.getTopicById(id);
    }


    @PostMapping
    public void createNewTopic(@RequestBody Topic topic){
        topicService.createNewTopic(topic);
    }

    @PutMapping(path="/{id}")
    public void updateTopic(@PathVariable("id") Long id, @RequestBody String name){
        topicService.updateTopic(id, name);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTopic(@PathVariable("id") Long id){
        topicService.deleteTopic(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
