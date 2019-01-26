package com.epam.multithreading.service.validator;

import com.epam.multithreading.service.factory.ClientFactory;
import com.epam.multithreading.service.reader.FileReader;
import com.epam.multithreading.service.reader.Reader;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

public class XsdValidatorTest {
    ClientFactory clientFactory = ClientFactory.getInstance();
    private Reader<File> fileReader;
    private Validator<File> xsdValidator;
    private String validXml;
    private String invalidXml;

    @BeforeTest
    public void init() {
        xsdValidator = clientFactory.getXsdValidator();
        fileReader = FileReader.getInstance();
        validXml = "src/test/resources/clients.xml";
        invalidXml = "src/test/resources/clients_invalid.xml";
    }

    @Test
    public void testIsValidSuccess() {
        File xml = fileReader.read(validXml);
        Assert.assertTrue(xsdValidator.isValid(xml));
    }


    @Test
    public void testIsValidFail() {
        File xml = fileReader.read(invalidXml);
        Assert.assertFalse(xsdValidator.isValid(xml));
    }
}
