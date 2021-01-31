package com.dotin;

import com.dotin.model.entity.Invertory;
import com.dotin.model.entity.Pay;
import com.dotin.model.repository.InvertoryRepository;
import com.dotin.model.repository.PayRepository;
import com.dotin.viwe.Meno;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        Meno.getInstance().createMeno();
    }
}
