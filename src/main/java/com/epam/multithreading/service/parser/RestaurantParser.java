package com.epam.multithreading.service.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantParser implements Parser<Integer> {

    private static Logger logger = LogManager.getLogger(ClientParser.class);


    private static RestaurantParser instance;

    public static RestaurantParser getInstance() {
        if (instance == null) {
            synchronized (RestaurantParser.class) {
                if (instance == null) {
                    instance = new RestaurantParser();
                }
            }
        }
        return instance;
    }

    public List<Integer> parse(File file) {

        List<Integer> integers = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            NodeList restaurantElements = document.getElementsByTagName("clients");
            for (int i = 0; i < restaurantElements.getLength(); i++) {
                Element number = (Element) restaurantElements.item(i);
                int result = Integer.parseInt(number.getAttribute("cash_boxes_amount"));
                if (result < 1) {
                    throw new IllegalArgumentException("Incorrect cash boxes amount");
                }
                integers.add(result);
            }

        } catch (ParserConfigurationException | SAXException | IOException | IllegalArgumentException e) {
            logger.error("Exception occurs while parsing: " + e.getMessage());
            throw new IllegalArgumentException(e);
        }
        return integers;
    }
}
