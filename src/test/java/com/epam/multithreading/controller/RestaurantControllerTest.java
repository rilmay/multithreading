package com.epam.multithreading.controller;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RestaurantControllerTest {
    private RestaurantController restaurantController;
    private String filePath;
    private String performanceFilePath;

    @BeforeTest
    public void init() {
        performanceFilePath = "src/test/resources/clients_performance.xml";
        filePath = "src/test/resources/clients.xml";
        restaurantController = RestaurantController.getInstance();
    }

    @Test
    public void testSingleton() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> hashCode = executorService.submit(() -> RestaurantController.getInstance().hashCode());
        executorService.shutdown();
        Assert.assertEquals(restaurantController.hashCode(), hashCode.get().intValue());
    }

    @Test
    public void testReadFileAnsStartProcess() {
        restaurantController.readFileAndStartProcess(filePath);

        Assert.assertTrue(Thread.activeCount() > 1);
    }

    @Test
    public void testReadFileAnsStartProcessPerformance() {
        Runtime runtime = Runtime.getRuntime();
        long beforeMemory = runtime.totalMemory() - runtime.freeMemory();
        restaurantController.readFileAndStartProcess(filePath);
        long memory = (runtime.totalMemory() - runtime.freeMemory()) - beforeMemory;
        System.out.println(memory);
        Assert.assertTrue(Thread.activeCount() > 8 && memory > 200_000);
    }
}
