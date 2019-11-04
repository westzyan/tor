package com.tor.pojo;

import java.util.Date;

public class BridgeRouter {

    private Integer id;
    private String ip;
    private String country;
    private String countryCode;
    private String city;
    private Float longitude;
    private Float latitude;

    public BridgeRouter(Integer id, String ip, String country, String countryCode, String city, Float longitude, Float latitude) {
        this.id = id;
        this.ip = ip;
        this.country = country;
        this.countryCode = countryCode;
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public BridgeRouter() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }
}
