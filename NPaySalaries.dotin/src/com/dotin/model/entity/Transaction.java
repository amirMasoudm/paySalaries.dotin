package com.dotin.model.entity;

public class Transaction {
    private float depositDebortNumber;
    private float depositCreditorNumber;
    private int transfer;

    public Transaction(float depositDebortNumber, float depositCreditorNumber, int transfer) {
        this.depositDebortNumber = depositDebortNumber;
        this.depositCreditorNumber = depositCreditorNumber;
        this.transfer = transfer;
    }

    public Transaction() {
    }

    public int getTransfer() {
        return transfer;
    }

    public void setTransfer(int transfer) {
        this.transfer = transfer;
    }

    public float getDepositDebortNumber() {
        return depositDebortNumber;
    }

    public void setDepositDebortNumber(float depositDebortNumber) {
        this.depositDebortNumber = depositDebortNumber;
    }

    public float getDepositCreditorNumber() {
        return depositCreditorNumber;
    }

    public void setDepositCreditorNumber(float depositCreditorNumber) {
        this.depositCreditorNumber = depositCreditorNumber;
    }
}
