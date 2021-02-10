package com.dotin.service;

import com.dotin.repository.PayRepository;

public class PayService {
    PayRepository payRepository = new PayRepository();

    public void paySalaries() {
        payRepository.paySalaries();
    }

}
