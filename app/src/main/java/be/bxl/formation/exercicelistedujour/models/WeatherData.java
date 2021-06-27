package be.bxl.formation.exercicelistedujour.models;

public class WeatherData {

    private String city;
    private double temp;
    private double humidity;
    private double latitude;
    private double longitude;
private String description;


    public WeatherData(String city, double temp, double humidity, double latitude, double longitude, String description) {
        this.city = city;
        this.temp = temp;
        this.humidity = humidity;
        this.latitude=latitude;
        this.longitude=longitude;
        this.description=description;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
