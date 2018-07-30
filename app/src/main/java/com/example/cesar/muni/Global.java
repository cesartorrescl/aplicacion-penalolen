package com.example.cesar.muni;


public class Global {
    private String ip;

    public Global() {
        this.ip = "http://192.168.0.16/";
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
