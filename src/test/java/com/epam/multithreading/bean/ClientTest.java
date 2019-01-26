package com.epam.multithreading.bean;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ClientTest {
    private Client client;

    @BeforeTest
    public void init() {
        client = new Client("John", 3, false);
        //client.setCashBox(new Semaphore(1));
    }

    @Test
    public void testRun() {
        Executors.newSingleThreadExecutor().execute(client);
        Assert.assertTrue(Thread.activeCount() > 1);
    }
}
