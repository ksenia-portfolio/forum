package com.ksenia.forum.controllers;

import com.ksenia.forum.models.Response;
import com.ksenia.forum.services.ResponseService;
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
@RequestMapping(path = "/v1/api/forum/responses")
public class ResponseController {

    private final ResponseService responseService;

    @GetMapping
    public List<Response> getAllResponses(){
        return responseService.getAllResponses();
    }

    @GetMapping(path="/email/{email}")
    public List<Response> getAllResponsesByEmail(@PathVariable("email") String email){
        return this.responseService.getAllResponsesByEmail(email);
    }

    @GetMapping(path="/by-message-id/{id}")
    public List<Response> getAllResponsesByMessageId(@PathVariable("id") Long id){
        return responseService.getAllResponsesByMessageId(id);
    }

    @GetMapping(path="/{id}")
    public Optional<Response> getResponseById(@PathVariable("id") Long id){
        return responseService.getResponseById(id);
    }


    @PostMapping
    public void createNewMResponse(@RequestBody Response response){
        responseService.createNewResponse(response);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteResponse(@PathVariable("id") Long id){
        responseService.deleteResponseById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}")
    public void increaseRating(@PathVariable Long id){
        responseService.increaseRating(id);
    }
}
