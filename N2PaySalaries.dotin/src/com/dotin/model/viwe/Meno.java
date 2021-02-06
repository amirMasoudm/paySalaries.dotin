package com.dotin.model.viwe;

import com.dotin.model.repository.InvertoryRepository;
import com.dotin.model.repository.PayRepository;

import java.util.Scanner;

public class Meno {
    private Meno() {}
    public static Meno meno=new Meno();
    public static Meno getInstance(){
        return meno;
    }

    public  void createMeno() {
        PayRepository payRepository = new PayRepository();
        InvertoryRepository invertoryRepository = new InvertoryRepository();
        Scanner getServiceNum = new Scanner(System.in);
        System.out.println("shomare service morede nazar ra vared konid");
        System.out.println("1:pay Salaries \t 2:create invertory list(1000 num,,have to choice for firest action) ");
        int getService = getServiceNum.nextInt();
        if (getService == 1) {
            payRepository.paySalaries();
            createMeno();
        } else if (getService == 2) {
            invertoryRepository.saveInvertoryList();
            createMeno();
        } else {
            System.out.println("1 ya 2");
            createMeno();
        }
    }
}
