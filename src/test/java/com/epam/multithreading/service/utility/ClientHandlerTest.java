package com.epam.multithreading.service.utility;

import com.epam.multithreading.bean.Client;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ClientHandlerTest {
    private ClientHandler clientHandler;
    private List<Client> clients;
    private int amount;

    @BeforeTest
    public void init() {
        clientHandler = ClientHandler.getInstance();
        clients = new ArrayList<>();
        clients.add(new Client("John", 1, false));
        clients.add(new Client("Violet", 1, false));
        amount = 4;
    }

    @Test
    public void testReturnProcessedClientList() {
        List<Client> processedClients = clientHandler.returnProcessedClientList(clients, amount);
        Assert.assertNotNull(processedClients.get(0).getSemaphore());

    }
}
