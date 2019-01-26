package com.epam.multithreading.service.validator;

import com.epam.multithreading.service.reader.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class XsdValidator implements Validator<File> {
    private static Logger logger = LogManager.getLogger(XsdValidator.class);
    private File xsd;

    public XsdValidator(String xsdPath) {
        FileReader fileReader = FileReader.getInstance();
        xsd = fileReader.read(xsdPath);
    }

    public boolean isValid(File input) {
        try {
            input = Optional.of(input).orElseThrow(() -> new IllegalArgumentException("Incorrect parsed file"));
            xsd = Optional.of(xsd).orElseThrow(() -> new IllegalArgumentException("Incorrect xsd file"));
            SchemaFactory factory = SchemaFactory
                    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsd));
            javax.xml.validation.Validator validator = schema.newValidator();
            validator.validate(new StreamSource(input));
            return true;
        } catch (SAXException | IOException | IllegalArgumentException e) {
            logger.error("Invalid file, cause: " + e);
            return false;
        }
    }
}

