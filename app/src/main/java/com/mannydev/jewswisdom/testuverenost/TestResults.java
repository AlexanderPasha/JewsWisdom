package com.mannydev.jewswisdom.testuverenost;

public class TestResults {
    private int countA;
    private int countB;
    private int countC;

    public TestResults(){
        this.countA=0;
        this.countB=0;
        this.countC=0;
    }

    public void addA(){
        countA++;
    }
    public void addB(){
        countB++;
    }
    public void addC(){
        countC++;
    }

    public String showResults(){
        if(getCountA() >= getCountB() && getCountA() >= getCountC()){
            return "A";
        }else if(getCountB() >= getCountA() && getCountB() >= getCountC()){
            return "B";
        }else if(getCountC() >= getCountA() && getCountC() >= getCountB()){
            return "C";
        }
        return "E";
    }

    private int getCountA() {
        return countA;
    }

    private int getCountB() {
        return countB;
    }

    private int getCountC() {
        return countC;
    }

    public int getResults(){
        return (countA+countB/2+countC/3)*100/7;
    }
}
