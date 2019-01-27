package com.epam.multithreading.bean;

import com.epam.multithreading.exception.MultithreadingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Client implements Runnable {
    private static Logger logger = LogManager.getLogger(Client.class);
    protected boolean preOrder = false;
    protected Semaphore semaphore;
    protected String name;
    protected int cashBoxNumber;

    public Client(String name, int cashBoxNumber, boolean preOrder) {
        this.preOrder = preOrder;
        this.name = name;
        this.cashBoxNumber = cashBoxNumber;
    }

    public Client() {
    }

    public boolean hasPreOrder() {
        return preOrder;
    }

    public void setPreOrder(boolean preOrder) {
        this.preOrder = preOrder;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCashBoxNumber() {
        return cashBoxNumber;
    }

    public void setCashBoxNumber(int cashBoxNumber) {
        this.cashBoxNumber = cashBoxNumber;
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(3);
            logger.info(name + " has arrived at restaurant at cash box " + cashBoxNumber);
            semaphore.acquire();
            TimeUnit.SECONDS.sleep(2);
            semaphore.release();
            logger.info(name + " took the order at cash box " + cashBoxNumber);
        } catch (InterruptedException e) {
            logger.error(e);
            throw new MultithreadingException(e);
        }
    }

    @Override
    public String toString() {
        return "Client{" +
                "name=" + name +
                ", cashBoxNumber=" + cashBoxNumber +
                ", preOrder=" + preOrder +
                '}';
    }
}
