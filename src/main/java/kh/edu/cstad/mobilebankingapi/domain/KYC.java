package kh.edu.cstad.mobilebankingapi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class KYC {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @Column(unique = true, nullable = false, length = 12)
    private String nationalCardId;

    @Column(nullable = false)
    private Boolean isVerified;


    @OneToOne
    private Customer customer;
}
