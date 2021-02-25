package com.ksenia.forum.controllers;

import com.ksenia.forum.models.Account;
import com.ksenia.forum.services.AccountService;
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
@RequestMapping(path = "/v1/api/forum/accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    @GetMapping(path="/{id}")
    public Optional<Account> getAccountById(@PathVariable("id") Long id){
        return accountService.getAccountById(id);
    }

    @GetMapping(path="/email/{email}")
    public Optional<Account> getAccountByEmail(@PathVariable("email") String email){
        return accountService.getAccountByEmail(email);
    }

    // admin tool
    @PostMapping(path="/register-admin")
    public void createAdminAccount(@RequestBody Account account){
        accountService.createAdmin(account);
    }

    // user tool
    @PostMapping(path="/register-user")
    public void createUserAccount(@RequestBody Account account){
        accountService.createUser(account);
    }

    @PutMapping(path="/{id}")
    public void updateAccount(@PathVariable("id") Long id, @RequestBody Account account){
        accountService.updateAccount(id, account);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAccount(@PathVariable("id") Long id){
        accountService.deleteAccount(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path="/authorisation")
    public Optional<Account> signIn(@RequestBody Account account){
        return accountService.authorise(account);
    }

}
