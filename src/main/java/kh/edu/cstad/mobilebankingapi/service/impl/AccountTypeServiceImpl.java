package kh.edu.cstad.mobilebankingapi.service.impl;

import kh.edu.cstad.mobilebankingapi.domain.AccountType;
import kh.edu.cstad.mobilebankingapi.dto.request.CreateAccountTypeRequest;
import kh.edu.cstad.mobilebankingapi.dto.response.AccountTypeResponse;
import kh.edu.cstad.mobilebankingapi.mapper.AccountMapper;
import kh.edu.cstad.mobilebankingapi.repository.AccountTypeRepository;
import kh.edu.cstad.mobilebankingapi.service.AccountTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService {
    private final AccountTypeRepository accountTypeRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountTypeResponse createAccountType(CreateAccountTypeRequest request) {
        String normalizedName = request.typeName().toUpperCase(Locale.ROOT);

        if(accountTypeRepository.existsAccountTypeByTypeName(normalizedName)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Account type already exists");
        }

        AccountType accountType = new AccountType();
        accountType.setTypeName(normalizedName);

        return accountMapper.toAccountTypeResponse(accountTypeRepository.save(accountType));
    }
}
