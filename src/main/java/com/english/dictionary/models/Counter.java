package com.english.dictionary.models;

/**
 * Base model for counter
 * @author Ihor Savchenko
 * @version 1.0
 */
public class Counter {

    private int countALL;
    private int countRemain;
    private int countForDone;

    public int getCountALL() {
        return countALL;
    }

    public void setCountALL(int countALL) {
        this.countALL = countALL;
    }

    public int getCountRemain() {
        return countRemain;
    }

    public void setCountRemain(int countRemain) {
        this.countRemain = countRemain;
    }

    public int getCountForDone() { return countForDone; }

    public void setCountForDone(int countForDone) { this.countForDone = countForDone; }
}
