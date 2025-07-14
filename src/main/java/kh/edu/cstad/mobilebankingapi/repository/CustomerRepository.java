package kh.edu.cstad.mobilebankingapi.repository;

import kh.edu.cstad.mobilebankingapi.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findAllByIsDeletedFalse();

    Boolean existsByPhoneNumber(String phoneNumber);

    Boolean existsByEmail(String email);

    Optional<Customer> findByPhoneNumber(String phoneNumber);

    Optional<Customer> findCustomerByPhoneNumberAndIsDeletedFalse(String phoneNumber);

    @Modifying
    @Query(value = """
        UPDATE Customer c
        SET c.isDeleted = TRUE
        WHERE c.phoneNumber = ?1
        """)
    void disableByPhoneNumber(String phoneNumber);

}
