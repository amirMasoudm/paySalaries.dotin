package com.dotin.model.service;

import com.dotin.model.repository.PayRepository;

import java.io.IOException;

public class PayService {
    PayRepository repository = new PayRepository();


    public void paySalaries() throws IOException {
        repository.paySalaries();
    }

    public void showPayFile() throws IOException {
        repository.showFile();
    }
}
