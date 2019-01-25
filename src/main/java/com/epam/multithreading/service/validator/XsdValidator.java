package com.epam.multithreading.service.validator;

import java.io.File;

public class XsdValidator implements Validator<File> {

    public XsdValidator(File xsd) {

    }

    public boolean isValid(File file) {
        return false;
    }
}

