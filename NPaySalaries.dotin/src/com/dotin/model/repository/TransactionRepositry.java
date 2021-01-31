package com.dotin.model.repository;

import com.dotin.model.entity.Pay;
import com.dotin.model.entity.Transaction;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionRepositry {
    private final Path path = Paths.get("..\\Transactions.txt");

    public void insertTXs(List<Transaction> transactionList) throws IOException {
        for (int i = 0; i < transactionList.size(); i++) {
            Transaction transaction = transactionList.get(i);
            String element = String.valueOf(transaction.getDepositDebortNumber()) + "\t" + String.valueOf(transaction.getDepositCreditorNumber()) + "\t" + String.valueOf(transaction.getTransfer()) + "\n";
            Files.write(path, element.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }
    }

    public void showFile() throws IOException {
        Stream<String> lines = Files.lines(path);
        String content = lines.collect(Collectors.joining("\t\n"));
        String[] contentArray = content.split("\t\n");
        for (int i = 0; i + 2 < contentArray.length; i++) {
            System.out.println(contentArray[i] + "\t");
            System.out.println(contentArray[i + 1] + "\t");
            System.out.println(contentArray[i + 2]);
        }
    }
}

