package kh.edu.cstad.mobilebankingapi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String fullName;

    @Column(unique = true, length = 100)
    private String email;

    @Column(unique = true, length = 15)
    private String phoneNumber;

    @Column(nullable = false, length = 10)
    private String gender;

    @Column(columnDefinition = "TEXT")
    private String remark;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isDeleted;


    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;


    @OneToOne(mappedBy = "customer")
    private KYC kyc;
}
