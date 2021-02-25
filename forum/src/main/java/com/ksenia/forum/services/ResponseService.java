package com.ksenia.forum.services;

import com.ksenia.forum.models.Response;
import com.ksenia.forum.repositories.ResponseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ResponseService {

    private final ResponseRepository responseRepository;

    public List<Response> getAllResponses(){
        return this.responseRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public List<Response> getAllResponsesByEmail(String email){
        return this.responseRepository.findAllByAccount_Email(email);
    }

    public List<Response> getAllResponsesByMessageId(Long id){
        return this.responseRepository.findAllByMessage_Id(id);
    }

    public Optional<Response> getResponseById(Long id){
        if(!responseRepository.existsById(id)){
            throw new IllegalStateException("Response with id " + id + " does not exist.");
        } return responseRepository.findById(id);
    }

    public void createNewResponse(Response response){
        responseRepository.saveAndFlush(response);
    }

    public void deleteResponseById(Long id){
        if(!responseRepository.existsById(id)){
            throw new IllegalStateException("Response with id " + id + " does not exist.");
        }
        responseRepository.deleteById(id);
    }

    @Transactional
    public void increaseRating(Long id){
        if(!responseRepository.existsById(id)){
            throw new IllegalStateException("Response with id " + id + " does not exist.");
        }
        responseRepository.findById(id).get().rateResponse();
    }


}
