package com.dotin.model.dto;

import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class PayVO implements Serializable {
    OprationType oprationType;
    String depositNumber;
    BigDecimal amount;
    final Path path = Paths.get("E:\\testProjectFuachers\\pay.txt");
    private final static Logger LOOGER = Logger.getLogger(Main.class);

    public PayVO(OprationType oprationType, String depositNumber, BigDecimal amount){
    this.amount=amount;
    this.depositNumber=depositNumber;
    this.oprationType=oprationType;
}

    @Override
    public String toString() {

    amount=amount.setScale(3, RoundingMode.HALF_DOWN);
        String element = oprationType+ "\t" + depositNumber + "\t" + String.valueOf(amount) + "\n";
        try {
            Files.write(path, element.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            return "inserted new pay";
        } catch (IOException e) {
            LOOGER.debug("insert to pay file has problem!! message::"+e.getMessage());
            return "insert to pay expired";
        }

    }

    public OprationType getOprationType() {
        return oprationType;
    }

    public void setOprationType(OprationType oprationType) {
        this.oprationType = oprationType;
    }

    public String getDepositNumber() {
        return depositNumber;
    }

    public void setDepositNumber(String depositNumber) {
        this.depositNumber = depositNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
