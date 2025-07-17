package kh.edu.cstad.mbapi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 32)
    private String actNo;

    @Column(nullable = false, length = 50)
    private String actName;

    @Column(nullable = false, length = 15)
    private String actCurrency;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private BigDecimal overLimit;

    @Column(nullable = false)
    private Boolean isHidden;

    @Column(nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(nullable = false, name = "cust_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne(optional = false)
    private AccountType accountType;

}
