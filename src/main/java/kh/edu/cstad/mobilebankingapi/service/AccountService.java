package kh.edu.cstad.mobilebankingapi.service;

import kh.edu.cstad.mobilebankingapi.dto.request.CreateAccountRequest;
import kh.edu.cstad.mobilebankingapi.dto.request.CustomerPhoneNumberRequest;
import kh.edu.cstad.mobilebankingapi.dto.request.UpdateAccountRequest;
import kh.edu.cstad.mobilebankingapi.dto.response.AccountResponse;

import java.util.List;

public interface AccountService {

    AccountResponse createAccount(CreateAccountRequest createAccountRequest);

    List<AccountResponse> findAll();

    AccountResponse findAccountByActNo(String actNo);

    void disableAccountByActNo(String actNo);

    void deleteAccountByActNo(String actNo);

    AccountResponse updateAccount(String actNo, UpdateAccountRequest request);

    List<AccountResponse> findAccountByCustomerPhone(CustomerPhoneNumberRequest customerPhoneNumberRequest);

}
