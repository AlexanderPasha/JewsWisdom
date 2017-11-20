package com.mannydev.jewswisdom.testmindsex;

public class TestMindSex {

    private int testCount;
    private static final boolean MALE = true;
    private boolean sex;

    public TestMindSex(){
        this.testCount=0;
    }

    public void addA(){
        if(sex==MALE){
            testCount=testCount+10;
        }else testCount=testCount+15;
    }
    public void addB(){
        testCount=testCount+5;

    }
    public void addC(){
        testCount=testCount-5;
    }

    public String showResults(){
        if(sex==MALE){
           if(testCount<50){
               return "MM";
           }else if(testCount>=50&&testCount<60){
               return "BOTH";
           }else return "MF";
        }else{
            if(testCount>60){
                return "FF";
            }else if(testCount>=50&&testCount<60){
                return "BOTH";
            }else return "FM";
        }
    }

    public int getResults(){
        return testCount;
    }


    public void setSex(boolean sex) {
        this.sex = sex;
    }
}
