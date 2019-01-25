package com.epam.multithreading.controller;

public class RestaurantController {
    private static RestaurantController instance;

    public static RestaurantController getInstance() {
        if (instance == null) {
            synchronized (RestaurantController.class) {
                if (instance == null) {
                    instance = new RestaurantController();
                }
            }
        }
        return instance;
    }

    public void readFileAndStartProcess(String path) {


    }
}
