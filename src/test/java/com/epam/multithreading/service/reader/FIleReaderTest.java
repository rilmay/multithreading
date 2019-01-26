package com.epam.multithreading.service.reader;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FIleReaderTest {
    private FileReader fileReader;
    private String filePath;
    private String invalidFilePath;

    @BeforeTest
    public void init() {
        filePath = "src/test/resources/clients.xml";
        invalidFilePath = "src/tttest";
        fileReader = FileReader.getInstance();
    }

    @Test
    public void testSingleton() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> hashCode = executorService.submit(() -> FileReader.getInstance().hashCode());
        executorService.shutdown();
        Assert.assertEquals(fileReader.hashCode(), hashCode.get().intValue());
    }

    @Test
    public void testRead() {
        File out = fileReader.read(filePath);
        Assert.assertEquals(new File(filePath).getAbsolutePath(), out.getAbsolutePath());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testReadFailed() {
        File out = fileReader.read(invalidFilePath);
        Assert.assertEquals(new File(invalidFilePath).getAbsolutePath(), out.getAbsolutePath());
    }
}
