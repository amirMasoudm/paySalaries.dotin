package com.dotin.model.repository;

import com.dotin.model.dto.InvertoryVO;
import com.dotin.model.viwe.Meno;
import org.apache.log4j.Logger;
import com.dotin.Main;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//import java.util.logging.Logger;

public class InvertoryRepository {
    private static final Path path = Paths.get("E:\\testProjectFuachers\\invertory.txt");
    private static final String firestDN = "1.10.100.";
    private final static Logger LOOGER = Logger.getLogger(Main.class);

    private static InvertoryRepository invertoryRepository = new InvertoryRepository();

    public static InvertoryRepository getInstance() {
        return invertoryRepository;
    }

    public static void saveInvertoryList() {
        for (int i = 1; i <= 1000; i++) {
            InvertoryVO invertoryVO = new InvertoryVO(generateRoundDN(), generateRoundAmount());
            invertoryVO.toString();
            LOOGER.debug("inserted inventory: " + i);
        }
        LOOGER.debug("List Created");
    }

    public static InvertoryVO findInverory(String depositNumber, List<String> invertoryArray) {
        InvertoryVO invertoryVO = null;
        for (int i = 2; i < invertoryArray.size(); i += 2) {
            String ElementDepositNumber = invertoryArray.get(i), zeroDepositNumber = invertoryArray.get(0);
            if (depositNumber.equals(ElementDepositNumber)) {
                invertoryVO = new InvertoryVO(ElementDepositNumber, BigDecimal.valueOf(Double.parseDouble(invertoryArray.get(i + 1))));
                break;
            } else if (depositNumber.equals(zeroDepositNumber)) {
                invertoryVO = new InvertoryVO(zeroDepositNumber, BigDecimal.valueOf(Double.parseDouble(invertoryArray.get(1))));
                break;
            }
        }
        return invertoryVO;
    }

    public void updateInvertories(List<InvertoryVO> invertoryList) {
        List<String> inveroryArray = findInvertoryFile();
        for (int i = 1; i + 2 < inveroryArray.size(); i++) {
            for (int j = 0; j < invertoryList.size(); j++) {
                InvertoryVO invertory = invertoryList.get(j);
                if (inveroryArray.get(0).equals(invertory.getDepositNumber())) {
                    inveroryArray.get(1).equals(String.valueOf(invertory.getAmount()));
                } else if (inveroryArray.get(1 + i).equals(invertory.getDepositNumber())) {
                    inveroryArray.get(i + 2).equals(String.valueOf(invertory.getAmount()));
                }
            }
        }
        insertInvertoryArray(inveroryArray);
    }

    public void insertInvertoryArray(List<String> invertoryArray) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            LOOGER.debug("insert to invertory Array Afret got it, has problem!!the  message::" + e.getMessage());
        }
        for (int i = 0; i < invertoryArray.size(); i += 2) {
            String amount = invertoryArray.get(i + 1);
            InvertoryVO invertoryVO = new InvertoryVO(invertoryArray.get(i), BigDecimal.valueOf(Double.parseDouble(amount)));
            invertoryVO.toString();
        }
    }

    public static List<String> findInvertoryFile() {

        List<String> invertoryFile = new ArrayList();
        try {
            Stream<String> lines = Files.lines(path);
            String invertoryContent = lines.collect(Collectors.joining("\t"));
            invertoryFile = Arrays.asList(invertoryContent.split("\t|\n"));
            LOOGER.debug("file is reading");
            return invertoryFile;
        } catch (IOException e) {
            LOOGER.error("find file has proble ,the message::" + e.getMessage()+" dose not exite");
            return invertoryFile;

        }

    }

    public static String generateRoundDN() {
        Random r = new Random();
        int low = 0,high = 1000;
        int result = r.nextInt(high - low) + low;
        String roundom = firestDN + String.valueOf(result);
        InvertoryVO hasNumber = findInverory(roundom, findInvertoryFile());
        if (hasNumber == null) {
            LOOGER.debug("generated DN>>>" + roundom);
            return roundom;
        } else {
            LOOGER.debug("GRN in else");
            return generateRoundDN();
        }
    }

    public static String generateRoundDNTX() {
        Random r = new Random();
        int low = 0,high = 1000;
        int result = r.nextInt(high - low) + low;
        String roundom = firestDN + String.valueOf(result);
        return roundom;
    }

    public static BigDecimal generateRoundAmount() {
        BigDecimal amount = BigDecimal.valueOf(new Random().nextDouble() * 10000);
        amount = amount.setScale(2, RoundingMode.HALF_DOWN);
        LOOGER.debug("normaly amount >>>>" + amount);
        return amount;
    }

    public static BigDecimal generateRoundAmountTX() {
        BigDecimal amount = BigDecimal.valueOf(new Random().nextDouble() * 100);
        amount = amount.setScale(2, RoundingMode.HALF_DOWN);
        LOOGER.debug("amount TX>>>" + amount);
        return amount;
    }
}
