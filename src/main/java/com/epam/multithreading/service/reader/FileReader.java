package com.epam.multithreading.service.reader;

import java.io.File;

public class FileReader implements Reader<File> {
    private static FileReader instance;

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
        return null;
    }
}
