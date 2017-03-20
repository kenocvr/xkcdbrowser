package com.labs.rucker.xkcdbrowser;

/**
 * Created by Carlos on 3/19/2017.
 */

public class ComicManager {

    public String getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(String currentNum) {
        this.currentNum = currentNum;
    }

    String currentNum;

    public String getNextNum() {
        return nextNum;
    }

    public void setNextNum(String nextNum) {
        this.nextNum = nextNum;
    }

    String nextNum;

    public String getPrevNum() {
        return prevNum;
    }

    public void setPrevNum(String prevNum) {
        this.prevNum = prevNum;
    }

    String prevNum;

    public String getRandNum() {
        return randNum;
    }

    public void setRandNum(String randNum) {
        this.randNum = randNum;
    }

    String randNum;


}
