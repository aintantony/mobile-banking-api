package kh.edu.cstad.mobilebankingapi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "customer_segments")
public class CustomerSegment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String segmentName;

    @Column(nullable = false)
    private BigDecimal overLimit;

    @Column(nullable = false)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "customerSegment")
    private List<Customer> customers;

}