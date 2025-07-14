package kh.edu.cstad.mobilebankingapi.controller;

import kh.edu.cstad.mobilebankingapi.dto.request.CreateCustomerRequest;
import kh.edu.cstad.mobilebankingapi.dto.request.UpdateCustomerRequest;
import kh.edu.cstad.mobilebankingapi.dto.response.CustomerResponse;
import kh.edu.cstad.mobilebankingapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse createNew(@RequestBody CreateCustomerRequest createCustomerRequest) {
        return customerService.createNew(createCustomerRequest);
    }

    @GetMapping
    public List<CustomerResponse> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{phoneNumber}")
    public CustomerResponse findByPhoneNumber(@PathVariable String phoneNumber) {
        return customerService.findByPhoneNumber(phoneNumber);
    }

    @PatchMapping("/{phoneNumber}")
    public CustomerResponse updateByPhoneNumber(@PathVariable String phoneNumber,
                                                @RequestBody UpdateCustomerRequest updateCustomerRequest) {
        return customerService.updateByPhoneNumber(phoneNumber ,updateCustomerRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{phoneNumber}")
    void disableByPhoneNumber(@PathVariable String phoneNumber) {
        customerService.disableByPhoneNumber(phoneNumber);
    }
}
