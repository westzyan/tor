package com.tor.pojo;

public class CountryOnionRelay {

    private String id;
    private Integer value;
    private Integer numBridge;

    public CountryOnionRelay(String id, Integer value, Integer numBridge) {
        this.id = id;
        this.value = value;
        this.numBridge = numBridge;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getNumBridge() {
        return numBridge;
    }

    public void setNumBridge(Integer numBridge) {
        this.numBridge = numBridge;
    }
}
