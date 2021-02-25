package com.ksenia.forum.controllers;

import com.ksenia.forum.models.ContactTopic;
import com.ksenia.forum.services.ContactTopicService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@AllArgsConstructor
@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(path = "/v1/api/contact-topics")
public class ContactTopicController {

    private final ContactTopicService topicService;

    @GetMapping
    public List<ContactTopic> getAllTopics(){
        return topicService.getAllTopics();
    }

}
