package com.ksenia.forum.repositories;

import com.ksenia.forum.models.ContactTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactTopicRepository extends JpaRepository<ContactTopic, Long> {

}
