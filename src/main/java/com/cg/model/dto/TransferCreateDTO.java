package com.cg.model.dto;

import com.cg.model.Customer;
import com.cg.model.Transfer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransferCreateDTO {

    private Long id;
    private Long senderId;
    private Long recipientId;
    private BigDecimal transferAmount;
    private Long fees;
    private BigDecimal feesAmount;
    private BigDecimal transactionAmount;

    public Transfer toTransfer(Customer sender, Customer recipient) {
        return new Transfer()
                .setId(id)
                .setSender(sender)
                .setRecipient(recipient)
                .setTransferAmount(transferAmount)
                .setFees(fees)
                .setFeesAmount(feesAmount)
                .setTransactionAmount(transactionAmount);
    }
}
