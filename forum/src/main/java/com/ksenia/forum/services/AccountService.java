package com.ksenia.forum.services;

import com.ksenia.forum.constants.Role;
import com.ksenia.forum.models.Account;
import com.ksenia.forum.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return this.accountRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Optional<Account> getAccountById(Long id) {
        boolean exists = accountRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Account with id " + id + " does not exist.");
        }
        return accountRepository.findById(id);
    }

    // get account by email
    public Optional<Account> getAccountByEmail(String email) {
        Optional<Account> accountOptional = accountRepository.findAccountByEmail(email);
        if (accountOptional.isEmpty()) {
            throw new IllegalStateException("Account with email " + email + " does not exist.");
        }
        return accountRepository.findAccountByEmail(email);
    }

    // create new admin account
    public void createAdmin(Account account) {
        Optional<Account> accountOptional = accountRepository.findAccountByEmail(account.getEmail());
        if (accountOptional.isPresent()) {
            throw new IllegalStateException("Account with email " + account.getEmail() + " already exists.");
        }
        account.setRole(Role.admin);
        accountRepository.saveAndFlush(account);
    }

    // create new user account
    public void createUser(Account account) {
        Optional<Account> accountOptional = accountRepository.findAccountByEmail(account.getEmail());
        if (accountOptional.isPresent()) {
            throw new IllegalStateException("Account with email " + account.getEmail() + " already exists.");
        }
        account.setRole(Role.user);
        accountRepository.saveAndFlush(account);
    }

    // update account details
    @Transactional
    public void updateAccount(Long id,
                              Account account) {
        Account accountOptional = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Account with id " + id + " does not exist."
                ));
        if (account.getName() != null &&
                account.getName().length() > 0 &&
                !Objects.equals(accountOptional.getName(), account.getName())) {
            accountOptional.setName(account.getName());
        }
        if (account.getEmail() != null &&
                account.getEmail().length() > 0 &&
                !Objects.equals(accountOptional.getEmail(), account.getEmail())) {
            Optional<Account> accountOptional2 = accountRepository.
                    findAccountByEmail(account.getEmail());
            if (accountOptional2.isPresent()) {
                throw new IllegalStateException("Email " + account.getEmail() + " is already used.");
            }
            accountOptional.setEmail(account.getEmail());
        }
        if (account.getPassword() != null &&
                account.getPassword().length() >= 8 &&
                !Objects.equals(accountOptional.getPassword(), account.getPassword())) {
            accountOptional.setPassword(account.getPassword());
        }
    }

    // delete account
    public void deleteAccount(Long id) {
        boolean exists = accountRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Account with id " + id + " does not exist.");
        }
        accountRepository.deleteById(id);
    }

    // authorise
    @Transactional
    public Optional <Account> authorise(Account account){
        Optional<Account> accountOptional = accountRepository.findAccountByEmail(account.getEmail());
        if(accountOptional.isEmpty()){
            throw new IllegalStateException("Account with email " + account.getEmail() + " does not exist.");
        }else if(!accountOptional.get().getPassword().contentEquals(account.getPassword())){
            throw new IllegalStateException("Invalid password.");
        }
        System.out.println(accountOptional);
        return accountOptional;
    }
}
