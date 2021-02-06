package com.dotin.model.repository;

import com.dotin.model.dto.InvertoryVO;
import com.dotin.model.dto.OprationType;
import com.dotin.model.dto.PayVO;
import com.dotin.model.dto.TransactionVO;
import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PayRepository {
    private static final Logger LOGGER = Logger.getLogger(Main.class);
    InvertoryRepository invertoryRepository = new InvertoryRepository();

    public void paySalaries() {
        List<InvertoryVO> invertoryList = new ArrayList();
        List<PayVO> payList = new ArrayList<>();
        List<TransactionVO> transactionList = new ArrayList<>();
        BigDecimal sumTX = new BigDecimal(0);
        sumTX = sumTX.setScale(2, RoundingMode.HALF_DOWN);
        String debtorDepositNumber = String.valueOf(InvertoryRepository.generateRoundDNTX());
        InvertoryVO debtorInvertory = invertoryRepository.findInverory(debtorDepositNumber, InvertoryRepository.findInvertoryFile());
        if (debtorInvertory != null) {
            for (int i = 0; i < 10; i++) {
                String creditorDepositNumber = InvertoryRepository.generateRoundDNTX();
                InvertoryVO creditorInvetory = invertoryRepository.findInverory(creditorDepositNumber, InvertoryRepository.findInvertoryFile());
                if (creditorInvetory != null) {
                    LOGGER.debug("credit inverory recived>>>" + i);
                    BigDecimal amount = InvertoryRepository.generateRoundAmountTX();
                    List<Object> lists = generatePayCreditor(debtorDepositNumber, creditorInvetory.getDepositNumber(), amount);
                    payList.add((PayVO) lists.get(0));
                    transactionList.add((TransactionVO) lists.get(1));
                    invertoryList.add((InvertoryVO) lists.get(2));
                    sumTX = sumTX.add(amount);
                } else if (creditorInvetory == null) {
                    LOGGER.debug("creditor generated dose not exist in list");
                    i--;
                }
            }
            if (debtorInvertory.getAmount().compareTo(sumTX) == 1 || debtorInvertory.getAmount().compareTo(sumTX) == 0) {
                payList.add(new PayVO(OprationType.debtor, debtorDepositNumber, sumTX));
                debtorInvertory.setAmount(debtorInvertory.getAmount().subtract(sumTX));
                invertoryList.add(debtorInvertory);
                goToDon(payList, transactionList, invertoryList);
                LOGGER.debug("pay salaries dose Done!!!");
            } else {
                LOGGER.debug("sum to pay is less than debtor invertory \n sum toPay= " + debtorInvertory.getAmount() + " debtor invertory= " + sumTX);
            }
        } else {
            LOGGER.debug("debtor generated dose not in invertoy list");
            paySalaries();
        }
    }

    public void goToDon(List<PayVO> payVOList, List<TransactionVO> TXlisst, List<InvertoryVO> invertoryVOList) {
        insertToPayFile(payVOList);
        TXRepository.getInstance().insertTXFile(TXlisst);
        invertoryRepository.updateInvertories(invertoryVOList);
    }

    public static List<Object> generatePayCreditor(String debtorDN, String creditorDepositNumber, BigDecimal amount) {
        List<Object> lists = new ArrayList<>();
        InvertoryRepository invertoryRepository = new InvertoryRepository();
        PayVO payCreditor = new PayVO(OprationType.creditor, creditorDepositNumber, amount);
        TransactionVO tx = new TransactionVO(debtorDN, creditorDepositNumber, amount);
        InvertoryVO creditorInvetory = invertoryRepository.findInverory(creditorDepositNumber, invertoryRepository.findInvertoryFile());
        creditorInvetory.setAmount(creditorInvetory.getAmount().add(amount));
        lists.add(payCreditor);
        lists.add(tx);
        lists.add(creditorInvetory);
        LOGGER.debug("genared pay creditor");
        return lists;
    }

    public void insertToPayFile(List<PayVO> payList) {
        PayVO a = payList.get(0);
        payList.set(0, payList.get(payList.size() - 1));
        payList.set(payList.size() - 1, a);
        for (int i = 0; i < payList.size(); i++) {
            PayVO pay = payList.get(i);
            pay.toString();
        }
    }
}

