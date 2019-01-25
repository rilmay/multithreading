package com.epam.multithreading.service.constant;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.*;

public class ConstantHandlerTest {
    private ConstantHandler constantHandler;

    @BeforeTest
    public void init() {
        constantHandler = ConstantHandler.getInstance();
    }

    @Test
    public void testSingleton() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> hashCode = executorService.submit(new Callable<Integer>() {
            public Integer call() {
                return ConstantHandler.getInstance().hashCode();
            }
        });
        executorService.shutdown();
        Assert.assertEquals(constantHandler.hashCode(), hashCode.get().intValue());
    }
}
