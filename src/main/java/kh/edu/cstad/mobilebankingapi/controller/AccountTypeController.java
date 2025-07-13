package kh.edu.cstad.mobilebankingapi.controller;

import jakarta.validation.Valid;
import kh.edu.cstad.mobilebankingapi.dto.request.CreateAccountTypeRequest;
import kh.edu.cstad.mobilebankingapi.dto.response.AccountTypeResponse;
import kh.edu.cstad.mobilebankingapi.service.AccountTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account-types")
@RequiredArgsConstructor
public class AccountTypeController {

    private final AccountTypeService accountTypeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountTypeResponse createAccountType(@Valid @RequestBody CreateAccountTypeRequest createAccountTypeRequest) {

        return accountTypeService.createAccountType(createAccountTypeRequest);
    }
}