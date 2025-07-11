package kh.edu.cstad.mobilebankingapi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {

    @Id
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String actNo;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private BigDecimal overLimit;

    @Column(nullable = false)
    private Boolean isDeleted;


    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;


    @ManyToOne
    @JoinColumn(name = "acc_type_id")
    private AccountType accountType;
}
