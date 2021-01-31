package com.dotin.model.service;

import com.dotin.model.repository.TransactionRepositry;

import java.io.IOException;

public class TransactionService {

    TransactionRepositry repositry = new TransactionRepositry();

    public void showTXFile() throws IOException {
        repositry.showFile();
    }
}
