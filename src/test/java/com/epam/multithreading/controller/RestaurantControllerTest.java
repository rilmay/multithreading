package com.epam.multithreading.controller;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.*;

public class RestaurantControllerTest {
    private static final int TIME_LIMIT = 1000;
    private static final int MEMORY_LIMIT = 100_000_000;
    private RestaurantController restaurantController;
    private String filePath;
    private String performanceFilePath;

    @BeforeTest
    public void init() {
        performanceFilePath = "src/test/resource/perfTest.xml";
        filePath = "src/test/resources/test.xml";
        restaurantController = RestaurantController.getInstance();
    }

    @Test
    public void testSingleton() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> hashCode = executorService.submit(new Callable<Integer>() {
            public Integer call() {
                return RestaurantController.getInstance().hashCode();
            }
        });
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

        long timeBefore = System.currentTimeMillis();
        restaurantController.readFileAndStartProcess(filePath);
        long time = System.currentTimeMillis() - timeBefore;
        long memory = (runtime.totalMemory() - runtime.freeMemory()) - beforeMemory;
        Assert.assertTrue(time <= TIME_LIMIT && memory <= MEMORY_LIMIT);
    }

}
