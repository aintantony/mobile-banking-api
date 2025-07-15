package kh.edu.cstad.mobilebankingapi.repository;

import kh.edu.cstad.mobilebankingapi.domain.KYC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KYCRepository extends JpaRepository<KYC, String> {

    boolean existsByNationalCardId(String nationalCardId);

    Optional<KYC> findByNationalCardId(String nationalCardId);

}
