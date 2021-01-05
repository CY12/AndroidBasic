package com.example.space.temp;

public class City {
    private String province;

    private String city;

    private County[] counties;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public County[] getCounties() {
        return counties;
    }

    public void setCounties(County[] counties) {
        this.counties = counties;
    }
}
