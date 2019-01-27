package com.epam.multithreading.controller;

import com.epam.multithreading.bean.Client;
import com.epam.multithreading.service.factory.ClientFactory;
import com.epam.multithreading.service.parser.Parser;
import com.epam.multithreading.service.reader.Reader;
import com.epam.multithreading.service.utility.ClientHandler;
import com.epam.multithreading.service.validator.Validator;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RestaurantController {
    private static volatile RestaurantController instance;

    public static RestaurantController getInstance() {
        if (instance == null) {
            synchronized (RestaurantController.class) {
                if (instance == null) {
                    instance = new RestaurantController();
                }
            }
        }
        return instance;
    }

    public void readFileAndStartProcess(String path) {
        ClientFactory clientFactory = ClientFactory.getInstance();
        Validator<File> xsdValidator = clientFactory.getXsdValidator();
        Parser<Client> clientParser = clientFactory.getClientParser();
        Parser<Integer> integerParser = clientFactory.getRestaurantParser();
        Reader<File> fileReader = clientFactory.getFileReader();
        ClientHandler clientHandler = clientFactory.getClientHandler();

        File parsed = fileReader.read(path);
        if (!xsdValidator.isValid(parsed)) {
            throw new IllegalArgumentException("Invalid file");
        }
        List<Client> clients = clientParser.parse(parsed);
        int cashBoxNumber = integerParser.parse(parsed).iterator().next();
        List<Client> processedClients = clientHandler.returnProcessedClientList(clients, cashBoxNumber);
        ExecutorService executorService = Executors.newFixedThreadPool(cashBoxNumber);
        processedClients.forEach(executorService::execute);
        executorService.shutdown();
    }
}
