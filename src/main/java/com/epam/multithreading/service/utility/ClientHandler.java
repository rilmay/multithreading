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
import java.util.stream.IntStream;

public class ClientHandler {

    private static Logger logger = LogManager.getLogger(ClientHandler.class);

    private static volatile ClientHandler instance;

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
        ClientValidator clientValidator = new ClientValidator(cahBoxAmount);
        if (!clients.stream().allMatch(clientValidator::isValid)) {
            logger.error("Incorrect list of clients");
            throw new IllegalArgumentException("Incorrect list of clients");
        }
        Map<Integer, Semaphore> cashBoxMap = new HashMap<>(cahBoxAmount);
        IntStream.range(1, cahBoxAmount + 1).forEach(i -> cashBoxMap.put(i, new Semaphore(1, true)));
        List<Client> processedClients = new ArrayList<>(clients.size());
        clients.forEach(i -> { i.setSemaphore(cashBoxMap.get(i.getCashBoxNumber()));
            processedClients.add(i.hasPreOrder()?new PreOrderClient(i, cashBoxMap.values()):i);
        });
        return processedClients;
    }
}
