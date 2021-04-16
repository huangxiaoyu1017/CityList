package com.example.testapril;

public class CityBean extends BaseIndexPinyinBean {

    private String city;//城市名字
    private String  cap;//是否是最上面的 不需要被转化成拼音的

    public CityBean(String city, String cap) {
        this.city = city;
        this.cap = cap;
    }


    public void setCap(String cap) {
        this.cap = cap;
    }

    public CityBean(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public String getCap() {
        return cap;
    }

    public CityBean setCity(String city, String cap) {
        this.city = city;
        this.cap = cap;
        return this;
    }


    @Override
    public String getTarget() {
        return city;
    }

    @Override
    public boolean isNeedToPinyin() {
        return true;
    }


    @Override
    public boolean isShowSuspension() {
        return  false;
    }
}