package com.dotin.model.dto;

import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TransactionVO implements Serializable {
    private final static Logger LOOGER = Logger.getLogger(Main.class);
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
        String element = debtorDN + "\t" + creditorDN+ "\t" + String.valueOf(amount + "\n");
        try {
            Files.write(path, element.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            return "Tx inserted";
        } catch (IOException e) {
            LOOGER.debug("insert to TxFaile has problem!! message::"+e.getMessage());
            return "Tx expired";
        }

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
