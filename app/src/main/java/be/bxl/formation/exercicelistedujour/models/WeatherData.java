package be.bxl.formation.exercicelistedujour.models;

public class WeatherData {

    private String city;
    private double temp;
    private double humidity;

    public WeatherData(String city, double temp, double humidity) {
        this.city = city;
        this.temp = temp;
        this.humidity = humidity;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }
}
