package kh.edu.cstad.mbapi.service;

import kh.edu.cstad.mbapi.dto.request.CreateCustomerRequest;
import kh.edu.cstad.mbapi.dto.response.CustomerResponse;
import kh.edu.cstad.mbapi.dto.request.UpdateCustomerRequest;

import java.util.List;

public interface CustomerService {

    void disableByPhoneNumber(String phoneNumber);

    CustomerResponse updateByPhoneNumber(String phoneNumber,
                                         UpdateCustomerRequest updateCustomerRequest);

    CustomerResponse findByPhoneNumber(String phoneNumber);

    List<CustomerResponse> findAll();

    CustomerResponse createNew(CreateCustomerRequest createCustomerRequest);

}
