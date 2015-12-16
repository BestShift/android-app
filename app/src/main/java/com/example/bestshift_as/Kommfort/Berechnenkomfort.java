package com.example.bestshift_as.Kommfort;

/**
 * Created by fitim on 16.12.2015.
 */
public class Berechnenkomfort {
    public boolean schalter=true;
    public int count=1;
    public int getkommfortcount(){
        return this.count;
    }
    public void setkommfortcount(int count){
        this.count=count;
    }
    public boolean getSchalter(){
        return this.schalter;
    }
    public void setSchalter(boolean schalter){
        this.schalter=schalter;
    }
}
