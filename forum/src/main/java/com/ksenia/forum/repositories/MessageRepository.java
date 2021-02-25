package com.ksenia.forum.repositories;

import com.ksenia.forum.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByEmail(String email);
    List<Message> findAllByTopic(String name);
}
