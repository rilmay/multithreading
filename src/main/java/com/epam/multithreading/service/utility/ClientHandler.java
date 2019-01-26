package com.epam.multithreading.service.utility;

import com.epam.multithreading.bean.Client;
import com.epam.multithreading.bean.PreOrderClient;
import com.epam.multithreading.service.validator.ClientValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class ClientHandler {

    private static Logger logger = LogManager.getLogger(ClientHandler.class);

    private static ClientHandler instance;

    public static ClientHandler getInstance() {
        if (instance == null) {
            synchronized (ClientHandler.class) {
                if (instance == null) {
                    instance = new ClientHandler();
                }
            }
        }
        return instance;
    }

    public List<Client> returnProcessedClientList(List<Client> clients, int cahBoxAmount) {
        ClientValidator clientValidator = ClientValidator.getInstance();
        Map<Integer, Semaphore> cashBoxMap = new HashMap<>();
        for (int i = 1; i <= cahBoxAmount; i++) {
            cashBoxMap.put(i, new Semaphore(1, true));
        }
        List<Client> processedClients = new ArrayList<>(clients.size());
        for (Client i : clients) {
            i.setSemaphore(cashBoxMap.get(i.getCashBoxNumber()));
            Client result = i;
            if (i.isPreOrder()) {
                result = new PreOrderClient(i, cashBoxMap.values());
            }
            processedClients.add(result);
        }
        if (!processedClients.stream().allMatch(clientValidator::isValid)) {
            logger.error("Incorrect clients");
            throw new IllegalArgumentException("Incorrect clients");
        }
        return processedClients;
    }
}