package kh.edu.cstad.mobilebankingapi.service.impl;

import kh.edu.cstad.mobilebankingapi.domain.Customer;
import kh.edu.cstad.mobilebankingapi.dto.request.CreateCustomerRequest;
import kh.edu.cstad.mobilebankingapi.dto.request.UpdateCustomerRequest;
import kh.edu.cstad.mobilebankingapi.dto.response.CustomerResponse;
import kh.edu.cstad.mobilebankingapi.mapper.CustomerMapper;
import kh.edu.cstad.mobilebankingapi.repository.CustomerRepository;
import kh.edu.cstad.mobilebankingapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponse createNew(CreateCustomerRequest createCustomerRequest) {

        if (customerRepository.existsCustomerByEmail(createCustomerRequest.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Email already exist");
        }

        if (customerRepository.existsCustomerByPhoneNumber(createCustomerRequest.phoneNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Phone number already exist");
        }

        Customer customer = customerMapper.fromCreateCustomerRequest(createCustomerRequest);

        customerRepository.save(customer);

        return customerMapper.toCustomerResponse(customer);
    }

    @Override
    public List<CustomerResponse> findAll() {
        List<Customer> customers = customerRepository.findAll();

        return customers
                .stream()
                .map(customerMapper::toCustomerResponse)
                .toList();

    }

    @Override
    public CustomerResponse findByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber)
                .map(customerMapper::toCustomerResponse)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Customer phone number does not exist")
                );
    }

    @Override
    public CustomerResponse updateByPhoneNumber(String phoneNumber, UpdateCustomerRequest updateCustomerRequest) {

        Customer customer = customerRepository
                .findByPhoneNumber(phoneNumber)
                        .orElseThrow(
                                () -> new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "customer phone number does not exist"
                                )
                        );

        customerMapper.toCustomerPartially(updateCustomerRequest, customer);

        customer = customerRepository.save(customer);


        return customerMapper.toCustomerResponse(customer);
    }
}
