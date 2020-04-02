package com.example.oscarplaza.stride.Entidades;

public class SeverUrl {
    private String url;
    private String header = "application/json";
    private String getbase = "http://strideapi.cedeus.cl/";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getGetbase() {
        return getbase;
    }

    public void setGetbase(String getbase) {
        this.getbase = getbase;
    }

    public SeverUrl() {
    }
    public String  GetUrlEndPoint(int i)
    {
        String url = "";
        switch(i){
            case 0:
                url = "api-token-auth/";
                break;
            case 1:
                url = "points/";
                break;
        }
        return getGetbase()+url;
    }
}
