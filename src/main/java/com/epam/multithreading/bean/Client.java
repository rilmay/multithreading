package com.epam.multithreading.bean;

import java.util.concurrent.Semaphore;

public class Client implements Runnable {
    private String name;
    private int cashBoxNumber;
    private boolean preOrder;
    private Semaphore cashBox;

    public Semaphore getCashBox() {
        return cashBox;
    }

    public void setCashBox(Semaphore cashBox) {
        this.cashBox = cashBox;
    }

    public Client() {
    }

    public Client(String name, int cashBoxNumber, boolean preOrder) {
        this.name = name;
        this.cashBoxNumber = cashBoxNumber;
        this.preOrder = preOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPreOrder() {
        return preOrder;
    }

    public void setCashBoxNumber(int cashBoxNumber) {
        this.cashBoxNumber = cashBoxNumber;
    }

    public void setPreOrder(boolean preOrder) {
        this.preOrder = preOrder;
    }

    public int getCashBoxNumber() {
        return cashBoxNumber;
    }

    public void run() {


    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name +
                ", cashBoxNumber=" + cashBoxNumber +
                ", preOrder=" + preOrder +
                '}';
    }
}
