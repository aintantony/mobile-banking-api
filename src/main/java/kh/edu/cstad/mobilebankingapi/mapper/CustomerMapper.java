package kh.edu.cstad.mobilebankingapi.mapper;

import kh.edu.cstad.mobilebankingapi.domain.Customer;
import kh.edu.cstad.mobilebankingapi.dto.request.CreateCustomerRequest;
import kh.edu.cstad.mobilebankingapi.dto.request.UpdateCustomerRequest;
import kh.edu.cstad.mobilebankingapi.dto.response.CustomerResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toCustomerPartially(UpdateCustomerRequest updateCustomerRequest,
                             @MappingTarget Customer customer);

    CustomerResponse toCustomerResponse(Customer customer);

    Customer fromCreateCustomerRequest(CreateCustomerRequest createCustomerRequest);

}
