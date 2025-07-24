package kh.edu.cstad.mbapi.controller;

import kh.edu.cstad.mbapi.dto.request.CreateAccountRequest;
import kh.edu.cstad.mbapi.dto.response.AccountResponse;
import kh.edu.cstad.mbapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AccountResponse createNew(CreateAccountRequest createAccountRequest) {
        return accountService.createNew(createAccountRequest);
    }
}
