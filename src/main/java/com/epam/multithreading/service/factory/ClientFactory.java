package com.epam.multithreading.service.factory;

import com.epam.multithreading.bean.Client;
import com.epam.multithreading.service.parser.ClientParser;
import com.epam.multithreading.service.parser.Parser;
import com.epam.multithreading.service.parser.RestaurantParser;
import com.epam.multithreading.service.reader.FileReader;
import com.epam.multithreading.service.reader.Reader;
import com.epam.multithreading.service.utility.ClientHandler;
import com.epam.multithreading.service.validator.Validator;
import com.epam.multithreading.service.validator.XsdValidator;

import java.io.File;

public class ClientFactory {
    private static final String XSD_PATH = "src/main/resources/Client.xsd";

    private static volatile ClientFactory instance;

    public static ClientFactory getInstance() {
        if (instance == null) {
            synchronized (ClientFactory.class) {
                if (instance == null) {
                    instance = new ClientFactory();
                }
            }
        }
        return instance;
    }

    public Validator<File> getXsdValidator() {
        return new XsdValidator(XSD_PATH);
    }

    public Reader<File> getFileReader() {
        return FileReader.getInstance();
    }

    public Parser<Client> getClientParser() {
        return ClientParser.getInstance();
    }

    public Parser<Integer> getRestaurantParser() {
        return RestaurantParser.getInstance();
    }

    public ClientHandler getClientHandler() {
        return ClientHandler.getInstance();
    }
}
