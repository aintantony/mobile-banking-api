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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponse createNew(CreateCustomerRequest createCustomerRequest) {

        if (customerRepository.existsByEmail(createCustomerRequest.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Email already exist");
        }

        if (customerRepository.existsByPhoneNumber(createCustomerRequest.phoneNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Phone number already exist");
        }

        Customer customer = customerMapper.fromCreateCustomerRequest(createCustomerRequest);
        customer.setIsDeleted(false);

        customerRepository.save(customer);

        return customerMapper.toCustomerResponse(customer);
    }

    @Override
    public List<CustomerResponse> findAll() {
        List<Customer> customers = customerRepository.findAllByIsDeletedFalse();

        return customers
                .stream()
                .map(customerMapper::toCustomerResponse)
                .toList();

    }

    @Override
    public CustomerResponse findByPhoneNumber(String phoneNumber) {
        return customerRepository.findCustomerByPhoneNumberAndIsDeletedFalse(phoneNumber)
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

    @Transactional
    @Override
    public void disableByPhoneNumber(String phoneNumber) {

        if (!customerRepository.existsByPhoneNumber(phoneNumber)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer phone number not exist");
        }

        customerRepository.disableByPhoneNumber(phoneNumber);

    }
}
