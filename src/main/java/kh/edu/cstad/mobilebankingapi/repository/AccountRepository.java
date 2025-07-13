package kh.edu.cstad.mobilebankingapi.repository;

import kh.edu.cstad.mobilebankingapi.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findAccountByActNo(String actNo);

    Optional<List<Account>> findAccountByCustomer_PhoneNumber(String customerPhoneNumber);

    Boolean existsByCustomer_PhoneNumberAndAccountTypeTypeName(String customerPhoneNumber, String accountTypeTypeName);

}
