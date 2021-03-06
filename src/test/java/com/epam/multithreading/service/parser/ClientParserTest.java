package com.epam.multithreading.service.parser;

import com.epam.multithreading.bean.Client;
import com.epam.multithreading.service.reader.FileReader;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class ClientParserTest {
    private Parser<Client> clientParser;
    private FileReader fileReader;
    private String filePath;

    @BeforeTest
    public void init() {
        filePath = "src/test/resources/clients(1).xml";
        fileReader = FileReader.getInstance();
        clientParser = new ClientParser();
    }

    @Test
    public void testParse() {
        List<Client> clients = clientParser.parse(fileReader.read(filePath));
        Assert.assertEquals("Client{name=Norina, cashBoxNumber=2, preOrder=true}", clients.get(0).toString());
    }
}
