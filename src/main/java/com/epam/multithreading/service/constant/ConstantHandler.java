package com.epam.multithreading.service.constant;

public class ConstantHandler {
    private static final int numberOfCashBoxes = 3;
    private static ConstantHandler instance;

    public static ConstantHandler getInstance() {
        if (instance == null) {
            synchronized (ConstantHandler.class) {
                if (instance == null) {
                    instance = new ConstantHandler();
                }
            }
        }
        return instance;
    }

    public int getNumberOfCashBoxes() {
        return numberOfCashBoxes;
    }
}
