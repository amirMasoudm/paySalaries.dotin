package com.dotin.model.repository;

import com.dotin.model.entity.Invertory;
import com.dotin.model.entity.Pay;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InvertoryRepository {
    private final Path path = Paths.get("..\\invertory.txt");

    public void showFile() throws IOException {
        Stream<String> lines = Files.lines(path);
        String content = lines.collect(Collectors.joining("\t\n"));
        String[] contentArray = content.split("\t\n");
        for (int i = 0; i + 1 < contentArray.length; i++) {
            System.out.println(contentArray[i] + "\t");
            System.out.println(contentArray[i + 1]);
        }
    }

    public void saveInvertoryList() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("insert size of invertories you want to insert");
        int invertorisSize = scanner.nextInt();
        for (int i = 1; i <= invertorisSize; i++) {
            System.out.println("insert Deposit Number " + i);
            String depositNumber = String.valueOf(scanner.nextFloat() + "\t");
            Files.write(path, depositNumber.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("insert amount of deposit Number " + i);
            String amount = String.valueOf(scanner.nextInt()) + "\n";
            Files.write(path, amount.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        }
        System.out.println("List Created");
    }

    public Invertory findInverory(float depositNumber) throws IOException {
        Stream<String> lines = Files.lines(path);
        String invertoryContent = lines.collect(Collectors.joining("\t"));
        String[] invertoryArray = invertoryContent.split("\t|\n");
        Invertory invertory = null;
        for (int i = 2; i < invertoryArray.length; i += 2) {
            float ElementDepositNumber = Float.parseFloat(invertoryArray[i]);
            float zeroDepositNumber = Float.parseFloat(invertoryArray[0]);
            if (depositNumber == ElementDepositNumber) {
                invertory = new Invertory();
                invertory.setDepositNumber(ElementDepositNumber);
                invertory.setAmount(Integer.parseInt(invertoryArray[i + 1]));
                return invertory;
            } else if (depositNumber == zeroDepositNumber) {
                invertory = new Invertory();
                invertory.setDepositNumber(zeroDepositNumber);
                invertory.setAmount(Integer.parseInt(invertoryArray[1]));
                return invertory;
            }
        }
        return invertory;
    }

    public void insertInvertoryArray(String[] invertoryArray) throws IOException {
        Files.delete(path);
        for (int i = 0; i < invertoryArray.length; i++) {
            String element = invertoryArray[i];
            if (i % 2 == 0) {
                element += "\t";
            } else element += "\n";

            Files.write(path, element.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }
    }

    public void updateInvertories(List<Invertory> invertoryList) throws IOException {
        Stream<String> lines = Files.lines(path);
        String invertoryContent = lines.collect(Collectors.joining("\t"));
        String[] inveroryArray = invertoryContent.split("\t|\n");
        for (int i = 1; i + 2 < inveroryArray.length; i++) {
            for (int j = 0; j < invertoryList.size(); j++) {
                Invertory invertory = invertoryList.get(j);
                if (inveroryArray[0].equals(String.valueOf(invertory.getDepositNumber()))) {
                    inveroryArray[1] = String.valueOf(invertory.getAmount());
                } else if (inveroryArray[i + 1].equals(String.valueOf(invertory.getDepositNumber()))) {
                    inveroryArray[i + 2] = String.valueOf(invertory.getAmount());
                }
            }
        }
        insertInvertoryArray(inveroryArray);
    }
}