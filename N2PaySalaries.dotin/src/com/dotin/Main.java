package com.dotin;

import com.dotin.repository.PayRepository;
import com.dotin.service.PayService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        PayService payService=new PayService();
        payService.paySalaries();

    }
}
