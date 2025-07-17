package kh.edu.cstad.mbapi.mapper;

import kh.edu.cstad.mbapi.domain.Customer;
import kh.edu.cstad.mbapi.dto.request.CreateCustomerRequest;
import kh.edu.cstad.mbapi.dto.response.CustomerResponse;
import kh.edu.cstad.mbapi.dto.request.UpdateCustomerRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toCustomerPartially(
            UpdateCustomerRequest updateCustomerRequest,
            @MappingTarget Customer customer
    );

    CustomerResponse toCustomerResponse(Customer customer);

    @Mapping(target = "customerSegment", ignore = true)
    Customer fromCreateCustomerRequest(CreateCustomerRequest createCustomerRequest);

}
