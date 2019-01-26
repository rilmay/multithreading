package com.epam.multithreading.service.validator;

import com.epam.multithreading.bean.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientValidator implements Validator<Client> {

    Logger logger = LogManager.getLogger(ClientValidator.class);

    private static ClientValidator instance;

    public static ClientValidator getInstance() {
        if (instance == null) {
            synchronized (ClientValidator.class) {
                if (instance == null) {
                    instance = new ClientValidator();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean isValid(Client client) {
        return (client.getCashBoxNumber() >= 1 && client.getName() != null &&
                !client.getName().isEmpty() && client.getSemaphore() != null);
    }
}
