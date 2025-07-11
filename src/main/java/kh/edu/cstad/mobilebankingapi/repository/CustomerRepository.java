package kh.edu.cstad.mobilebankingapi.repository;

import kh.edu.cstad.mobilebankingapi.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Boolean existsCustomerByPhoneNumber(String phoneNumber);

    Boolean existsCustomerByEmail(String email);

    Optional<Customer> findByPhoneNumber(String phoneNumber);

}
