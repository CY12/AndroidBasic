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
    // 如何想合并某个分支的提交 可以在remote查看该分支提交的记录 可以ctrl多选 然后右键cherry—pick 有冲突合并就可以，合并好后作为一次新的commit提交
}
