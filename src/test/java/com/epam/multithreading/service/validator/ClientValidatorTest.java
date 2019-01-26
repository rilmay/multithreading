package com.epam.multithreading.service.validator;

import com.epam.multithreading.bean.Client;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ClientValidatorTest {
    private Client correct;
    private Client incorrect;
    private Validator<Client> clientValidator;

    @BeforeTest
    public void init() {
        clientValidator = new ClientValidator(3);
        correct = new Client("John", 3, true);
        incorrect = new Client();
        incorrect.setName("Bla");
    }

    @Test
    public void testIsValidSuccess() {
        Assert.assertTrue(clientValidator.isValid(correct));
    }

    @Test
    public void testIsValidFailed() {
        Assert.assertFalse(clientValidator.isValid(incorrect));
    }
}
