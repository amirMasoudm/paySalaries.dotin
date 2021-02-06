package com.dotin;

import com.dotin.model.repository.InvertoryRepository;
import com.dotin.model.dto.InvertoryVO;
import com.dotin.model.repository.PayRepository;
import com.dotin.model.viwe.Meno;
import org.apache.log4j.Logger;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Meno.getInstance().createMeno();
    }


}
