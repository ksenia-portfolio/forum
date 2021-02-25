package com.ksenia.forum.repositories;

import com.ksenia.forum.models.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {

    List<Response> findAllByMessage_Id(Long id);
    List<Response> findAllByAccount_Email(String email);
}
