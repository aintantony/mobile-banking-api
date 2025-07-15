package kh.edu.cstad.mobilebankingapi.repository;

import kh.edu.cstad.mobilebankingapi.domain.CustomerSegment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerSegmentRepository extends JpaRepository<CustomerSegment, Integer> {

    Optional<CustomerSegment> getCustomerSegmentBySegmentName(String segmentName);

}
