package com.epam.multithreading.service.parser;

import com.epam.multithreading.service.reader.FileReader;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class RestaurantParserTest {
    private Parser<Integer> restaurantParser;
    private FileReader fileReader;
    private String filePath;

    @BeforeTest
    public void init() {
        filePath = "src/test/resources/clients(2).xml";
        fileReader = FileReader.getInstance();
        restaurantParser = new RestaurantParser();
    }

    @Test
    public void testParse() {
        List<Integer> clients = restaurantParser.parse(fileReader.read(filePath));
        Assert.assertEquals(10, clients.get(0).intValue());
    }
}
