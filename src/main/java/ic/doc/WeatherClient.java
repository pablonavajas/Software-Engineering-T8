package ic.doc;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

import ic.doc.WeatherForecast;

import java.util.LinkedHashMap;
import java.util.Map;

public class WeatherClient {

  private String location;
  private String date;

  private final Forecaster forecaster;
  private Forecast forecast;
  private WeatherForecast weatherForecast;

  static LinkedHashMap<String, WeatherForecast> cache;

  public boolean cachedData = false;

  private final double maxTime = 3600000;

  WeatherClient() {

    this.forecaster = new Forecaster();
    this.cache = new LinkedHashMap<String, WeatherForecast>();

  }

  WeatherClient(int maxSize) {

    this.forecaster = new Forecaster();
    this.cache = new LinkedHashMap<String, WeatherForecast>() {
      protected boolean removeEldestEntry(Map.Entry<String, WeatherForecast> eldest) {
        return size() > maxSize;
      }
    };
  }

  public Forecast check(String location, String date) {

    this.location = location.toUpperCase();
    this.date = date.toUpperCase();

    if (cache.containsKey(this.location + this.date)) {

      this.weatherForecast = cache.get(this.location + this.date);
      this.cachedData = true;

    } else {

      this.forecast = forecaster.forecastFor(Region.valueOf(this.location), Day.valueOf(this.date));
      this.weatherForecast = new WeatherForecast(forecast.temperature(), forecast.summary());
      cache.put((this.location + this.date), this.weatherForecast);


      this.cachedData = false;
    }
    return forecast;
  }

  public String summary() {
    return weatherForecast.getDescription();
  }

  public int temperature() {
    return weatherForecast.getTemp();
  }

  public boolean dataCached() {
    return this.cachedData;
  }
}
