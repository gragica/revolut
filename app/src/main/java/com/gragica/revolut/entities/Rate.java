package com.gragica.revolut.entities;

public class Rate {

    public float amount;
    public String code;

    public Rate(String s, float f){
        amount = f;
        code = s;
    }

}
