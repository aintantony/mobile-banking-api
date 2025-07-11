package kh.edu.cstad.mobilebankingapi.service;

import kh.edu.cstad.mobilebankingapi.dto.request.CreateCustomerRequest;
import kh.edu.cstad.mobilebankingapi.dto.request.UpdateCustomerRequest;
import kh.edu.cstad.mobilebankingapi.dto.response.CustomerResponse;

import java.util.List;

/**
 * Service interface for managing customer-related operations
 * in the Mobile Banking API.
 *
 * Provides methods to create, retrieve, and update customer data.
 *
 * @author Antony
 * @since 1.0
 */
public interface CustomerService {

    /**
     * Creates a new customer using the provided request data.
     *
     * @param createCustomerRequest the request object containing customer details
     * @return the created customer's information as a response DTO
     */
    CustomerResponse createNew(CreateCustomerRequest createCustomerRequest);

    /**
     * Retrieves all customers in the system.
     *
     * @return a list of all customers represented as response DTOs
     */
    List<CustomerResponse> findAll();

    /**
     * Finds a customer by their phone number.
     *
     * @param phoneNumber the phone number of the customer to find
     * @return the customer's information as a response DTO
     */
    CustomerResponse findByPhoneNumber(String phoneNumber);

    /**
     * Updates customer information based on the given phone number.
     *
     * @param phoneNumber the phone number of the customer to update
     * @param updateCustomerRequest the request object containing updated customer details
     * @return the updated customer's information as a response DTO
     */
    CustomerResponse updateByPhoneNumber(String phoneNumber, UpdateCustomerRequest updateCustomerRequest);
}

