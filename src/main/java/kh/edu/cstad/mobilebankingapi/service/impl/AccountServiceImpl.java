package kh.edu.cstad.mobilebankingapi.service.impl;

import kh.edu.cstad.mobilebankingapi.domain.Account;
import kh.edu.cstad.mobilebankingapi.domain.AccountType;
import kh.edu.cstad.mobilebankingapi.domain.Customer;
import kh.edu.cstad.mobilebankingapi.dto.request.CreateAccountRequest;
import kh.edu.cstad.mobilebankingapi.dto.request.CustomerPhoneNumberRequest;
import kh.edu.cstad.mobilebankingapi.dto.request.UpdateAccountRequest;
import kh.edu.cstad.mobilebankingapi.dto.response.AccountResponse;
import kh.edu.cstad.mobilebankingapi.mapper.AccountMapper;
import kh.edu.cstad.mobilebankingapi.repository.AccountRepository;
import kh.edu.cstad.mobilebankingapi.repository.AccountTypeRepository;
import kh.edu.cstad.mobilebankingapi.repository.CustomerRepository;
import kh.edu.cstad.mobilebankingapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountResponse createAccount(CreateAccountRequest request) {

        Customer customer = customerRepository.findByPhoneNumber(request.phoneNumber())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        // validation account type
        AccountType accountType = accountTypeRepository.findAccountTypeByTypeName(request.accountType().toUpperCase(Locale.ROOT))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account type not found"));

        String typeName = request.accountType().toUpperCase(Locale.ROOT);
        String phoneNumber = request.phoneNumber();

        if (accountRepository.existsByCustomer_PhoneNumberAndAccountTypeTypeName(phoneNumber, typeName)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Customer already has an account of this type");
        }

        Account account = accountMapper.customerRequestToAccount(request);
        account.setCustomer(customer);
        account.setAccountType(accountType);
        account.setOverLimit(BigDecimal.valueOf(10000));
        account.setIsDeleted(false);

        return accountMapper.accountToAccountResponse(accountRepository.save(account));
    }

    @Override
    public List<AccountResponse> findAll() {
        List<AccountResponse> accounts = accountRepository.findAll().stream()
                .filter(account -> account.getIsDeleted().equals(false))
                .map(accountMapper::accountToAccountResponse)
                .collect(Collectors.toList());
        if (accounts.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }

        return accounts;
    }

    @Override
    public AccountResponse findAccountByActNo(String actNo) {

        return accountRepository.findAccountByActNo(actNo)
                .filter(account -> account.getIsDeleted().equals(false))
                .map(accountMapper::accountToAccountResponse)
                .orElseThrow(()  -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
    }

    @Override
    public void disableAccountByActNo(String actNo) {
        Account accountToDelete = accountRepository.findAccountByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        accountToDelete.setIsDeleted(true);

        accountRepository.save(accountToDelete);
    }

    @Override
    public void deleteAccountByActNo(String actNo) {
        Account deleteAccount = accountRepository.findAccountByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        accountRepository.delete(deleteAccount);

    }

    @Override
    public AccountResponse updateAccount(String actNo, UpdateAccountRequest request) {

        Account accountToUpdate = accountRepository.findAccountByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        accountMapper.toAccountPartially(request,accountToUpdate);

        return accountMapper.accountToAccountResponse(accountRepository.save(accountToUpdate));
    }

    @Override
    public List<AccountResponse> findAccountByCustomerPhone(CustomerPhoneNumberRequest customerPhoneNumberRequest) {

        if (!customerRepository.existsCustomerByPhoneNumber(customerPhoneNumberRequest.phoneNumber())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }

        List<Account> accounts = accountRepository.findAccountByCustomer_PhoneNumber(customerPhoneNumberRequest.phoneNumber())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));

        return accounts.stream()
                .filter(account -> account.getIsDeleted().equals(false))
                .map(accountMapper::accountToAccountResponse)
                .collect(Collectors.toList());
    }
}
