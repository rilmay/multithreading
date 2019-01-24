package com.epam.multithreading.bean;

public class Client implements Runnable{
    private int cashBoxNumber;
    private boolean privelege;
    private CashBox cashBox;

    public CashBox getCashBox() {
        return cashBox;
    }

    public void setCashBox(CashBox cashBox) {
        this.cashBox = cashBox;
    }

    public Client() {
    }

    public Client(int cashBoxNumber, boolean privelege) {
        this.cashBoxNumber = cashBoxNumber;
        this.privelege = privelege;
    }

    public void setCashBoxNumber(int cashBoxNumber) {
        this.cashBoxNumber = cashBoxNumber;
    }

    public void setPrivelege(boolean privelege) {
        this.privelege = privelege;
    }

    public int getCashBoxNumber() {
        return cashBoxNumber;
    }

    public boolean getPrivelege(){
        return privelege;
    }

    public void run() {


    }
}
