package com.ksenia.forum.services;

import com.ksenia.forum.models.ContactTopic;
import com.ksenia.forum.repositories.ContactTopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ContactTopicService {

    private ContactTopicRepository contactTopicRepository;

    public List<ContactTopic> getAllTopics(){
        return this.contactTopicRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
}
