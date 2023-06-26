package com.apper;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// @RestController - handles HTTP requests and returns responses. @RequestMapping - specifies the base path for all the methods in the controller. Here, /account
@RestController
@RequestMapping("account")
public class AccountController {
    private final AccountService accountService;
    public AccountController(AccountService accountService) { // receives an instance of 'AccountService' as parameter which is ...
        this.accountService = accountService; // assigned to 'accountService'
    }

    @PostMapping // handles HTTP POST requests
    @ResponseStatus(HttpStatus.CREATED) // specify status code to be returned upon successful execution
    public CreateAccountResponse createAccount(@RequestBody CreateAccountRequest request) {
        Account account = accountService.create(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword());

        CreateAccountResponse response = new CreateAccountResponse();
        response.setVerificationCode(account.getVerificationCode());
        response.setGeneratedId(account.getId());

        return response;
    }

    @GetMapping("{accountId}") // Add this annotation to handle GET requests for "/account/account ID"
    public GetAccountResponse getAccount(@PathVariable String accountId) {
        Account account = accountService.get(accountId);

        GetAccountResponse response = new GetAccountResponse();
        response.setBalance(account.getBalance());
        response.setFirstName(account.getFirstName());
        response.setLastName(account.getLastName());
        response.setUsername(account.getUsername());
        response.setRegistrationDate(account.getCreationDate());
        response.setAccountId(account.getId());
        return response;
    }

    @GetMapping("") // Add this annotation to handle GET requests for "localhost:8080/account"
    // ADDITIONAL NOTE: Add this annotation @GetMapping("all") to handle GET requests for "localhost:8080/account/all"
    public List<GetAccountResponse> getAllAccounts() {
        List<GetAccountResponse> responseList = new ArrayList<>();

        for (Account account : accountService.getAll()) {
            GetAccountResponse response = new GetAccountResponse();
            response.setBalance(account.getBalance());
            response.setFirstName(account.getFirstName());
            response.setLastName(account.getLastName());
            response.setUsername(account.getUsername());
            response.setRegistrationDate(account.getCreationDate());
            response.setAccountId(account.getId());
            responseList.add(response);
        }

        return responseList;
    }

    @PutMapping("{accountId}") // { } is a dynamic value. This handles HTTP PUT requests and updates an account based on the provided 'accountId'.
    @ResponseStatus(HttpStatus.OK)
    public UpdateAccountResponse updateAccount(@PathVariable String accountId, @RequestBody CreateAccountRequest request) {
        accountService.update(accountId, request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword());

        UpdateAccountResponse response = new UpdateAccountResponse();
        response.setLastUpdate(LocalDateTime.now());

        return response;
    }

    @DeleteMapping("{accountId}") // this expects a { } dynamic value for the 'accountId' path variable.
    @ResponseStatus(HttpStatus.NO_CONTENT) // HTTP status is set to '204 NO_CONTENT'
    public void deleteAccount(@PathVariable String accountId) { // does not return any response since 'void'
        accountService.delete(accountId);
    }
}