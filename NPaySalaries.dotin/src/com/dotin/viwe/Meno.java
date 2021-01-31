package com.dotin.viwe;

import com.dotin.model.service.InvertoryService;
import com.dotin.model.service.PayService;
import com.dotin.model.service.TransactionService;

import java.io.IOException;
import java.util.Scanner;

public class Meno {
    private Meno() {
    }

    public static Meno meno = new Meno();

    public static Meno getInstance() {
        return meno;
    }

    PayService payService = new PayService();
    InvertoryService invertoryService = new InvertoryService();
    TransactionService TXService = new TransactionService();

    public void createMeno() throws IOException {
        Scanner getServiceNum = new Scanner(System.in);
        System.out.println("shomare service morede nazar ra vared konid");
        System.out.println("1: pardakhte hoghogh \t 2:sakht ya ezafe kardan be list mojodiha \t 3:moshahede etelate hesab \n" +
                " 4:moshahede liste mojodiha  \t 5:moshahede liste pardakhtha\t 6:moshahede liste lage pardakhtha");
        int getService = getServiceNum.nextInt();
        switch (getService) {
            case 1:
                payService.paySalaries();
                createMeno();
            case 2:
                invertoryService.createInvertoyList();
                createMeno();
            case 3:
                invertoryService.findInvertory();
                createMeno();
            case 4:
                invertoryService.showInvertoryFile();
                createMeno();
            case 5:
                payService.showPayFile();
                createMeno();

            case 6:
                TXService.showTXFile();
                createMeno();

        }
    }
}
