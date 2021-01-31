package com.dotin.model.repository;

import com.dotin.model.entity.Invertory;
import com.dotin.model.entity.Pay;
import com.dotin.model.entity.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PayRepository {
    private final Path path = Paths.get("..\\pay.txt");
    InvertoryRepository invertoryRepository = new InvertoryRepository();

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

    public void insertToPayFile(List<Pay> payList) throws IOException {
        Pay a = payList.get(0);
        payList.set(0, payList.get(payList.size() - 1));
        payList.set(payList.size() - 1, a);
        for (int i = 0; i < payList.size(); i++) {
            Pay pay = payList.get(i);
            String element = pay.getCoustomerState() + "\t" + String.valueOf(pay.getDepositNumber()) + "\t" + String.valueOf(pay.getAmount()) + "\n";
            Files.write(path, element.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }
    }

    public void paySalaries() throws IOException {

        Scanner scanValue = new Scanner(System.in);
        List<Invertory> invertoryList = new ArrayList();
        List<Pay> payList = new ArrayList<>();
        List<Transaction> transactionList = new ArrayList<>();
        int sumTX = 0;

        System.out.println("shomare hesabe morede bardash ra vared konid");
        float debtorDepositNumber = scanValue.nextFloat();
        Invertory debtorInvertory = invertoryRepository.findInverory(debtorDepositNumber);
        if (debtorInvertory != null) {
            System.out.println("mojodi shoma:\t"+debtorInvertory.getAmount());
            System.out.println("tedade tarakonesh haye morede nazar ra vared konid");
            int countTX = scanValue.nextInt();
            for (int i = 1; i <= countTX; i++) {
                System.out.println("shomare hesabe tarakoneshe " + i + " ra vared konid");
                float creditorDepositNumber = scanValue.nextFloat();
                System.out.println("meghdare morede nazar jahat variz ra vared konid");
                int amount = scanValue.nextInt();
                Invertory creditorInvetory = invertoryRepository.findInverory(creditorDepositNumber);
                if (creditorInvetory != null) {
                    sumTX += amount;
                    Pay payCreditor = new Pay("creditor", creditorDepositNumber, amount);
                    payList.add(payCreditor);

                    Transaction tx = new Transaction(debtorDepositNumber, creditorDepositNumber, amount);
                    transactionList.add(tx);

                    creditorInvetory.setAmount(amount += creditorInvetory.getAmount());
                    invertoryList.add(creditorInvetory);

                } else if (creditorInvetory == null) {
                    System.out.println("in shomare hesab vojod nadarad");
                    i--;
                }
            }
        } else {
            System.out.println("in shomare hesab vojod nadarad ");
            paySalaries();
        }
        if (debtorInvertory.getAmount() >= sumTX) {

            Pay payDeptor = new Pay("debtor", debtorDepositNumber, sumTX);
            payList.add(payDeptor);
            insertToPayFile(payList);

            debtorInvertory.setAmount(debtorInvertory.getAmount() - sumTX);
            invertoryList.add(debtorInvertory);
            InvertoryRepository invertoryRepository = new InvertoryRepository();
            invertoryRepository.updateInvertories(invertoryList);

            TransactionRepositry TXRepository = new TransactionRepositry();
            TXRepository.insertTXs(transactionList);
            System.out.println("pardakhte hoghogh anjam shod");

        } else {
            System.out.println("mojodie shoma kamtar az majmoe pardakhti hast");
            System.out.println("mojodi= " + debtorInvertory.getAmount() + "  majmoe pardakhti= " + sumTX);
        }
    }
}

