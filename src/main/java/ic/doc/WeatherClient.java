package ic.doc;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

import java.util.LinkedHashMap;
import java.util.Map;

public class WeatherClient {

  private String location;
  private String date;

  private final Forecaster forecaster;
  private Forecast forecast;

  static LinkedHashMap<String, Forecast> cache;

  public boolean cachedData = false;

  WeatherClient() {

    this.forecaster = new Forecaster();
    this.cache = new LinkedHashMap<String, Forecast>();

  }

  WeatherClient(int maxSize) {

    this.forecaster = new Forecaster();
    this.cache = new LinkedHashMap<String, Forecast>() {
      protected boolean removeEldestEntry(Map.Entry<String, Forecast> eldest) {
        return size() > maxSize;
      }
    };
  }

  public Forecast check(String location, String date) {

    this.location = location.toUpperCase();
    this.date = date.toUpperCase();

    if (cache.containsKey(this.location + this.date)) {

      this.forecast = cache.get(this.location + this.date);
      this.cachedData = true;

    } else {

      this.forecast = forecaster.forecastFor(Region.valueOf(this.location), Day.valueOf(this.date));
      cache.put((this.location + this.date), this.forecast);


      this.cachedData = false;
    }
    return forecast;
  }

  public String summary() {
    return forecast.summary();
  }

  public int temperature() {
    return forecast.temperature();
  }

  public boolean dataCached() {
    return this.cachedData;
  }
}
