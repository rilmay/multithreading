package com.epam.multithreading.service.validator;

public interface Validator<T> {
    boolean isValid(T t);
}
