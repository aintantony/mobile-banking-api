package kh.edu.cstad.mbapi.mapper;

import kh.edu.cstad.mbapi.domain.Account;
import kh.edu.cstad.mbapi.dto.request.CreateAccountRequest;
import kh.edu.cstad.mbapi.dto.request.UpdateAccountRequest;
import kh.edu.cstad.mbapi.dto.response.AccountResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(source = "accountType.type", target = "accountType")
    AccountResponse toAccountResponse(Account account);

    List<AccountResponse> toAccountResponseList(List<Account> accounts);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toAccountPartially(
            UpdateAccountRequest updateAccountRequest,
            @MappingTarget Account account);
}
