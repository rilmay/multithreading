package com.epam.multithreading.service.validator;

import com.epam.multithreading.service.reader.FileReader;
import com.epam.multithreading.service.reader.Reader;
import org.junit.Ignore;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

public class XsdValidatorTest {
    private Reader<File> fileReader = new FileReader();
    private XsdValidator xsdValidator;
    private String xsdPath;
    private String validXml;
    private String invalidXml;

    @BeforeTest
    public void init() {
        fileReader = FileReader.getInstance();
        xsdPath = "src/java/resources/clients.xsd";
        validXml = "src/test/resources/clients1.xml";
        invalidXml = "src/java/resources/clients2.xml";
    }

    @Test
    public void testIsValidSuccess() {
        File xml = fileReader.read(validXml);
        File xsd = fileReader.read(xsdPath);
        //xsdValidator = new XsdValidator(xsd);
        Assert.assertTrue(xsdValidator.isValid(xml));
    }


    @Test
    public void testIsValidFail() {
        File xml = fileReader.read(invalidXml);
        File xsd = fileReader.read(xsdPath);
        //xsdValidator = new XsdValidator(xsd);
        Assert.assertFalse(xsdValidator.isValid(xml));
    }
}
