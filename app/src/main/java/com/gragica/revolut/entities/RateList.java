package com.gragica.revolut.entities;


import java.io.Serializable;
import java.util.HashMap;


public class RateList implements Serializable {

    public String baseCurrency;
    public HashMap<String, Float> rates;

}
