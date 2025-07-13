package kh.edu.cstad.mobilebankingapi.service;

import kh.edu.cstad.mobilebankingapi.dto.request.CreateAccountTypeRequest;
import kh.edu.cstad.mobilebankingapi.dto.response.AccountTypeResponse;

public interface AccountTypeService {

    AccountTypeResponse createAccountType(CreateAccountTypeRequest createAccountTypeRequest);

}

