package com.epam.multithreading.service.reader;

public interface Reader<T> {
    T read(String path);
}
