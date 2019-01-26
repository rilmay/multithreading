package com.epam.multithreading.bean;

import com.epam.multithreading.exception.MultithreadingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class PreOrderClient extends Client {

    private static Logger logger = LogManager.getLogger(PreOrderClient.class);
    private Collection<Semaphore> semaphores;

    public Collection<Semaphore> getSemaphores() {
        return semaphores;
    }

    public void setSemaphores(Collection<Semaphore> semaphores) {
        this.semaphores = semaphores;
    }

    public PreOrderClient(Client client, Collection<Semaphore> semaphores) {
        super.name = client.name;
        super.semaphore = client.semaphore;
        super.cashBoxNumber = client.cashBoxNumber;
        super.preOrder = client.preOrder;
        this.semaphores = semaphores;
    }

    @Override
    public void run() {
        if (!isPreOrder()) {
            throw new IllegalArgumentException();
        }
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        logger.info(super.name + " has arrived at restaurant with pre order");
        try {
            Semaphore freeCashBox;
            int i = 0;
            searchingForFreeCashBox:
            while (true) {
                for (Semaphore sem : semaphores) {
                    i++;
                    if (sem.tryAcquire()) {
                        freeCashBox = sem;
                        break searchingForFreeCashBox;
                    }
                }
                i = 0;
            }
            TimeUnit.SECONDS.sleep(2);
            freeCashBox.release();
            logger.info(name + " took the order at cash box " + i);
        } catch (InterruptedException e) {
            logger.error(e);
            throw new MultithreadingException(e);
        }
    }
}
