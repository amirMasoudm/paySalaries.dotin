package com.dotin;

import com.dotin.dto.InvertoryVO;
import com.dotin.dto.PayVO;
import com.dotin.repository.InvertoryRepository;
import com.dotin.repository.PayRepository;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        InvertoryRepository invertoryRepository=new InvertoryRepository();
        PayRepository payRepository=new PayRepository();

        invertoryRepository.generateInventoryFile();
        payRepository.genratePaymentFile();

        List<PayVO> getPaymentFile=payRepository.getPaymentFile();
        List<InvertoryVO> getInvertoryFile=invertoryRepository.findInventoryFile();

        payRepository.paySalaries(getPaymentFile,getInvertoryFile);

    }
}
