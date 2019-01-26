package com.epam.multithreading.service.validator;

import com.epam.multithreading.bean.Client;

public class ClientValidator implements Validator<Client> {
    int amountOfCasBoxes;

    public ClientValidator(int amountOfCasBoxes) {
        this.amountOfCasBoxes = amountOfCasBoxes;
    }


    @Override
    public boolean isValid(Client client) {
        return (client.getCashBoxNumber() <= amountOfCasBoxes
                && client.getCashBoxNumber() >= 1 && client.getName() != null &&
                !client.getName().isEmpty());
    }
}
