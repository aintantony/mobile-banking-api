package kh.edu.cstad.mobilebankingapi.mapper;

import kh.edu.cstad.mobilebankingapi.domain.Account;
import kh.edu.cstad.mobilebankingapi.domain.AccountType;
import kh.edu.cstad.mobilebankingapi.dto.request.CreateAccountRequest;
import kh.edu.cstad.mobilebankingapi.dto.request.UpdateAccountRequest;
import kh.edu.cstad.mobilebankingapi.dto.response.AccountResponse;
import kh.edu.cstad.mobilebankingapi.dto.response.AccountTypeResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "actNo", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "accountType", ignore = true)
    Account customerRequestToAccount(CreateAccountRequest createAccountRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toAccountPartially(UpdateAccountRequest updateAccountRequest, @MappingTarget Account account);

    @Mapping(target = "accountType", source = "account.accountType.typeName")
    AccountResponse accountToAccountResponse(Account account);

    AccountTypeResponse toAccountTypeResponse(AccountType accountType);

}
