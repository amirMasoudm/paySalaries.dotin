package com.dotin.model.service;

import com.dotin.model.entity.Invertory;
import com.dotin.model.repository.InvertoryRepository;

import java.io.IOException;
import java.util.Scanner;

public class InvertoryService {
    InvertoryRepository repository = new InvertoryRepository();

    public void createInvertoyList() throws IOException {
        repository.saveInvertoryList();
    }

    public void findInvertory() throws IOException {
        Scanner getDepositNumber = new Scanner(System.in);
        System.out.println("shomare Hesabe morede nazar ra vared konid");
        float depositNumber = getDepositNumber.nextFloat();
        Invertory invertory = repository.findInverory(depositNumber);
        System.out.println("shomare hesab= " + invertory.getDepositNumber() + "\tmojodi =" + invertory.getAmount());
    }

    public void showInvertoryFile() throws IOException {
        repository.showFile();
    }
}
