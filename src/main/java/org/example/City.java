package org.example;

public class City {
    private Integer code;
    private String name;

    public City(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode(){
        return this.code;
    }

    public String getName(){
        return this.name;
    }
}
