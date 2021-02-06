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
import java.util.Objects;

public class InvertoryVO implements Serializable {
    private final static Logger LOOGER = Logger.getLogger(Main.class);

    String depositNumber;
    BigDecimal amount;

    public InvertoryVO(String depositNumber, BigDecimal amount) {
        this.depositNumber = depositNumber;
        this.amount = amount;
    }

    public InvertoryVO() {
    }

    @Override
    public String toString() {
        final Path path = Paths.get("E:\\testProjectFuachers\\invertory.txt");
        try {
            String element = depositNumber + "\t" + String.valueOf(amount) + "\n";
            Files.write(path, element.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            return "new Inventory inserted ";
        } catch (IOException e) {
            LOOGER.debug("cannot insert to invertoy file beacus :"+e.getMessage());
            return e.getMessage();
        }

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