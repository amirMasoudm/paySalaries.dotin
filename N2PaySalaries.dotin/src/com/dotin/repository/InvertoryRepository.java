package com.dotin.repository;

import com.dotin.dto.InvertoryVO;

import org.apache.log4j.Logger;
import com.dotin.Main;

import java.io.IOException;
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


public class InvertoryRepository {


    private static Path path = Paths.get("hear Type to set your invertory file Address");
    private static final String firestDN = "1.10.100.";
    private final static Logger LOOGER = Logger.getLogger(Main.class);
    private static InvertoryRepository invertoryRepository = new InvertoryRepository();

    public static InvertoryRepository getInstance() {
        return invertoryRepository;
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

    public List<String> findInvertoryFile() {
        List<String> invertoryFile = new ArrayList();
        try {
            String rootPath = String.valueOf(path.getRoot());
            if (rootPath.equals("null")) {
                path = Paths.get(System.getProperty("user.home") + "\\invertory.txt");
            }
            Stream<String> lines = Files.lines(path);
            String invertoryContent = lines.collect(Collectors.joining("\t"));
            invertoryFile = Arrays.asList(invertoryContent.split("\t|\n"));
            LOOGER.debug("file is reading");
            return invertoryFile;
        } catch (IOException d) {
            LOOGER.error("find file has problem ,the message::" + d.getMessage() + " dose not exite");
            path = Paths.get(System.getProperty("user.home") + "\\invertory.txt");
            return createInvertoryList();
        }

    }

    public List<String> createInvertoryList() {
        Set<String> arr = new LinkedHashSet<>();
        List<String> arry = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            arr.add(generateRoundDNTX());
            if (arr.size() < i) i--;
        }
        for (String s : arr) {
            BigDecimal inveAmount = generateRoundAmount();
            InvertoryVO invertoryVO = new InvertoryVO(s, inveAmount);
            insertToFile(invertoryVO);
            arry.add(s + "\t" + inveAmount + "\n");
        }
        return arry;
    }

    public void insertToFile(InvertoryVO invertoryVO) {
        try {
            Files.write(path, invertoryVO.toString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            LOOGER.debug("new invertoty inserted");
        } catch (IOException e) {
            path = Paths.get(System.getProperty("user.home") + "\\invertory.txt");
            try {
                Files.write(path, invertoryVO.toString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException ioException) {
                LOOGER.fatal(ioException.getMessage());
            }
        }
    }

    public void updateInvertories(List<InvertoryVO> invertoryList) {
        List<String> inveroryArray = findInvertoryFile();
        for (int i = 1; i + 2 < inveroryArray.size(); i++) {
            for (int j = 0; j < invertoryList.size(); j++) {
                InvertoryVO invertory = invertoryList.get(j);
                if (inveroryArray.get(0).equals(invertory.getDepositNumber())) {
                    inveroryArray.set(1, String.valueOf(invertory.getAmount()));
                } else if (inveroryArray.get(1 + i).equals(invertory.getDepositNumber())) {
                    inveroryArray.set(i + 2, String.valueOf(invertory.getAmount()));
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
            BigDecimal am = new BigDecimal(amount);
            InvertoryVO invertoryVO = new InvertoryVO(invertoryArray.get(i), am);
            insertToFile(invertoryVO);
        }
    }


    public String generateRoundDN() {
        Random r = new Random();
        int low = 0, high = 1000;
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
        int low = 0, high = 1000;
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
