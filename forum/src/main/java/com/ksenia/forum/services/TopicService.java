package com.ksenia.forum.services;

import com.ksenia.forum.models.Topic;
import com.ksenia.forum.repositories.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public List<Topic> getAllTopics() {
        return topicRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public Optional<Topic> getTopicById(Long id) {
        boolean exists = topicRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Topic with id " + id + " does not exist.");
        }
        return topicRepository.findById(id);
    }

    public void createNewTopic(Topic topic) {
        Optional<Topic> topicRep = topicRepository.findByName(topic.getName().toLowerCase());
        if(topicRep.isPresent()){
            throw new IllegalStateException("Topic with name " + topic.getName() + " already exists.");
        }topicRepository.saveAndFlush(topic);
    }

    @Transactional
    public void updateTopic(Long id,
                              String name) {
        Topic topicRepo = topicRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Topic with id " + id + " does not exist."
                ));
        if (name.length() > 0 &&
                !Objects.equals(topicRepo.getName(), name)) {
            topicRepo.setName(name.toLowerCase());
        }
    }

    public void deleteTopic(Long id) {
        boolean exists = topicRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Topic with id " + id + " does not exist.");
        }
        topicRepository.deleteById(id);
    }
}
