package com.dotin.model.entity;

import java.io.Serializable;

public class Pay implements Serializable {
    private String coustomerState;
    private float depositNumber;
    private int amount;

    public Pay(String customerState, float depositNumber, int amount) {
        this.coustomerState = customerState;
        this.depositNumber = depositNumber;
        this.amount = amount;
    }

    public float getDepositNumber() {
        return depositNumber;
    }

    public void setDepositNumber(float depositNumber) {
        this.depositNumber = depositNumber;
    }

    public String getCoustomerState() {
        return coustomerState;
    }

    public void setCoustomerState(String coustomerState) {
        this.coustomerState = coustomerState;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
