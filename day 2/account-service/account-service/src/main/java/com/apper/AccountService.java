package com.apper;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private List<Account> accounts = new ArrayList<>();
    private final IdGeneratorService IdGeneratorService;

    public AccountService(IdGeneratorService idGeneratorService) { // CONSTRUCTOR of the 'AccountService'.
        this.IdGeneratorService = idGeneratorService; // It receives an instance of 'IdGeneratorService as a parameter and assigns it to the 'IdGeneratorService' field.
    }

    public Account create(String firstName, String lastName, String username, String clearPassword) {
        Account account = new Account();

        // TODO: PART 1 TASK 1: Create 'IdGeneratorService' `generateRandomCharacters(int length)` and `getNextId()`. Make this a spring bean.
        String id = IdGeneratorService.getNextId(); // TODO: PART 1 TASK 3: Use `getNextId()` for generating accountId.
        System.out.println("Generated Id: " + id);
        account.setId(id);
        account.setBalance(1_000.0);

        LocalDateTime now = LocalDateTime.now();
        account.setCreationDate(now);
        account.setLastUpdated(now);

        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setUsername(username);
        account.setClearPassword(clearPassword);
        //TODO: PART 2 TASK 2: Use `generateRandomCharacters` for generating verificationCode (size: 6)
        //// ORIGINAL CODE: account.setVerificationCode("QW345T");
        account.setVerificationCode(IdGeneratorService.generateRandomCharacters(6));

        accounts.add(account);

        return account;
    }

    public Account get(String accountId) {
        for (Account account : accounts) {
            if (account.getId().equals(accountId)) {
                return account;
            }
        }

        return null;
    }

    public List<Account> getAll() {
        return accounts;
    }

    // TODO: PART 2 TASK 1 - UPDATE ACCOUNT
    public void update(String accountId, String firstName, String lastName, String username, String clearPassword) {
        Account account = get(accountId);

        account.setLastUpdated(LocalDateTime.now());

        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setUsername(username);
        account.setClearPassword(clearPassword);

        accounts.set(accounts.indexOf(account), account);
    }

    // TODO: PART 2 TASK 2 - DELETE ACCOUNT
    public void delete(String accountId) {
        Account account = get(accountId);
        accounts.remove(account);
    }
}