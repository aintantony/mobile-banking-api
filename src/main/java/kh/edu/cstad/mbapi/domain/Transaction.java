package kh.edu.cstad.mbapi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(length = 50)
    private String remark;

    @Column(nullable = false, length = 25)
    private String status;

    @Column(nullable = false)
    private Boolean isDeleted;

    @ManyToOne(optional = false)
    private TransactionType transactionType;

    @ManyToOne(optional = false)
    private Account sender;

    @ManyToOne(optional = false)
    private Account receiver;
}
