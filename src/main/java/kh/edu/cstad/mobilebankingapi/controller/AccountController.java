package kh.edu.cstad.mobilebankingapi.controller;

import jakarta.validation.Valid;
import kh.edu.cstad.mobilebankingapi.dto.request.CreateAccountRequest;
import kh.edu.cstad.mobilebankingapi.dto.request.CustomerPhoneNumberRequest;
import kh.edu.cstad.mobilebankingapi.dto.request.UpdateAccountRequest;
import kh.edu.cstad.mobilebankingapi.dto.response.AccountResponse;
import kh.edu.cstad.mobilebankingapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse createAccount(@Valid @RequestBody CreateAccountRequest createAccountRequest) {
        return accountService.createAccount(createAccountRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AccountResponse> findAllAccounts() {
        return accountService.findAll();
    }

    @GetMapping("/{actNo}")
    @ResponseStatus(HttpStatus.OK)
    public AccountResponse findAccountByActNo(@PathVariable String actNo) {
        return accountService.findAccountByActNo(actNo);
    }

    @GetMapping("/by-customer-name")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountResponse> findAccountByCustomer(@Valid @RequestBody CustomerPhoneNumberRequest customerPhoneNumberRequest) {
        return accountService.findAccountByCustomerPhone(customerPhoneNumberRequest);
    }

    @PatchMapping("/{actNo}")
    public AccountResponse updateAccount(@PathVariable String actNo, @RequestBody UpdateAccountRequest updateAccountRequest) {
        return accountService.updateAccount(actNo, updateAccountRequest);
    }

    @PutMapping("/disable/{actNo}")
    public ResponseEntity<?> disableAccountByActNo(@PathVariable String actNo) {
        accountService.disableAccountByActNo(actNo);
        return ResponseEntity.status(HttpStatus.OK).body("Your account is successfully disabled");
    }

    @DeleteMapping("/{actNo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountByActNo(@PathVariable String actNo) {
        accountService.deleteAccountByActNo(actNo);
    }

}
