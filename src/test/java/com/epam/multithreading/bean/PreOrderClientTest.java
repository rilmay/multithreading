package com.epam.multithreading.bean;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class PreOrderClientTest {
    private PreOrderClient client;

    @BeforeTest
    public void init() {
        Collection<Semaphore> semaphores = new ArrayList<>();
        semaphores.add(new Semaphore(1));
        client = new PreOrderClient(new Client("John", 1, true), semaphores);
        client.setSemaphore(new Semaphore(1));
    }

    @Test
    public void testRun() {
        Executors.newSingleThreadExecutor().execute(client);
        Assert.assertTrue(Thread.activeCount() > 1);
    }
}
