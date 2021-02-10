package com.dotin.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TransactionVO implements Serializable {
    private final Path path = Paths.get("E:\\testProjectFuachers\\Transactions.txt");
    String debtorDN;
    String creditorDN;
    BigDecimal amount;

    public TransactionVO(String debtorDN, String creditorDN, BigDecimal amount) {

        this.debtorDN = debtorDN;
        this.creditorDN = creditorDN;
        this.amount = amount;
    }

    public TransactionVO() {
    }

    @Override
    public String toString() {
        return debtorDN + "\t" + creditorDN + "\t" + amount + "\n";
    }

    public String getDebtorDN() {
        return debtorDN;
    }

    public void setDebtorDN(String debtorDN) {
        this.debtorDN = debtorDN;
    }

    public String getCreditorDN() {
        return creditorDN;
    }

    public void setCreditorDN(String creditorDN) {
        this.creditorDN = creditorDN;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
