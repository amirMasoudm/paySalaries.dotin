package com.dotin.model.repository;

import com.dotin.model.dto.TransactionVO;
import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class TXRepository {

    private static TXRepository txRepository = new TXRepository();

    public static TXRepository getInstance() {
        return txRepository;
    }

    public void insertTXFile(List<TransactionVO> transactionList) {
        for (int i = 0; i < transactionList.size(); i++) {
            TransactionVO transaction = transactionList.get(i);
            transaction.toString();
        }

    }
}
