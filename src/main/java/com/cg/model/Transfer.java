package com.cg.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transfers")
@Accessors(chain = true)
public class Transfer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Customer sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Customer recipient;

    @Column(name = "transfer_amount", precision = 12, nullable= false)
    private BigDecimal transferAmount;

    @Column(nullable= false)
    private Long fees;

    @Column(name = "fees_amount", precision = 12, nullable= false)
    private BigDecimal feesAmount;

    @Column(name = "transaction_amount", precision = 12, nullable= false)
    private BigDecimal transactionAmount;


    public Transfer(Customer sender, Customer recipient, BigDecimal transferAmount, Long fees, BigDecimal transactionAmount) {
        this.sender = sender;
        this.recipient = recipient;
        this.transferAmount = transferAmount;
        this.fees = fees;
        this.transactionAmount = transactionAmount;
    }

}
