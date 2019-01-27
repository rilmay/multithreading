package com.epam.multithreading.service.parser;

import com.epam.multithreading.bean.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientParser implements Parser<Client> {
    private static Logger logger = LogManager.getLogger(ClientParser.class);

    private static volatile ClientParser instance;

    public static ClientParser getInstance() {
        if (instance == null) {
            synchronized (ClientParser.class) {
                if (instance == null) {
                    instance = new ClientParser();
                }
            }
        }
        return instance;
    }

    public List<Client> parse(File file) {

        List<Client> clients = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            NodeList restaurantElements = document.getElementsByTagName("client");
            for (int i = 0; i < restaurantElements.getLength(); i++) {
                Node client = restaurantElements.item(i);
                NodeList elements = client.getChildNodes();
                Client parsed = new Client();

                for (int j = 0; j < elements.getLength(); j++) {
                    if (elements.item(j).getNodeType() != Node.TEXT_NODE) {
                        Node element = elements.item(j);
                        String field = element.getNodeName();
                        switch (field) {
                            case "name":
                                parsed.setName(element.getTextContent());
                                break;
                            case "cash_box_number":
                                parsed.setCashBoxNumber(Integer.parseInt(element.getTextContent()));
                                break;
                            case "pre-order":
                                parsed.setPreOrder(Boolean.parseBoolean(element.getTextContent()));
                                break;
                        }
                    }
                }
                clients.add(parsed);
            }

        } catch (ParserConfigurationException | SAXException | IOException | IllegalArgumentException e) {
            logger.error("Exception occurs while parsing: " + e);
            throw new IllegalArgumentException(e);
        }
        return clients;
    }
}
