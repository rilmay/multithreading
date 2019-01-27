package com.epam.multithreading.service.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader implements Reader<File> {
    private static Logger logger = LogManager.getLogger(FileReader.class);
    private static volatile FileReader instance;

    public static FileReader getInstance() {
        if (instance == null) {
            synchronized (FileReader.class) {
                if (instance == null) {
                    instance = new FileReader();
                }
            }
        }
        return instance;
    }

    public File read(String path) {
        if (!Files.exists(Paths.get(path))) {
            logger.error("File does not exist or not a file");
            throw new IllegalArgumentException("File does not exist or not a file");
        }
        return new File(path);
    }
}
