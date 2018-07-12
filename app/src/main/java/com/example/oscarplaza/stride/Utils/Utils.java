package com.example.oscarplaza.stride.Utils;


import com.example.oscarplaza.stride.R;

public class Utils {
    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    private String URL;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    private String response;

    public Utils(String URL) {
        this.URL = URL;
    }
    public Utils() {
    }
    public String generateurlmaps(String lat ,String lng)
    {
        String URL = "http://maps.google.com/maps/api/geocode/json?latlng="+lat+","+lng+"&sensor=false";
        setURL(URL);
        return getURL();
    }


    public int GetRangeName(int progress) {
        int output = 0;
        switch(progress){

            case 0:
            case 100:
                output = R.string.Kid;

            break;
            case 1:

            case 133:
                output = R.string.adole;

            break;
            case 2:

            case 167:
                output = R.string.adults;

            break;
            case 3:

            case 200:
            case 250:
                output = R.string.Elderly;

            break;
        }
        return  output;
    }
    public int Sex(int id){
        int output = 0;
        switch (id){
            case 2131230875:
                output =  R.string.no_define;
                break;
            case 2131230876:
                output = R.string.female;
                break;
            case 2131230877:
                output = R.string.male;
        }
        return output;

    }
}
