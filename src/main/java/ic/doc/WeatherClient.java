package ic.doc;

import com.weather.*;

import java.io.IOError;
import java.util.HashMap;

public class WeatherClient {

  private String location;
  private String date;

  private Forecaster forecaster;
  private Forecast forecast;

  static HashMap<String, String> cache = new HashMap<String, String>();

  WeatherClient (String location, String date) {

    this.location = location.toUpperCase();
    this.date = date.toUpperCase();

    forecaster = new Forecaster();
    forecast = forecaster.forecastFor(Region.valueOf(location), Day.valueOf(date));

    cache = (HashMap<String,String>);
    
  }

  public String summary() {
    return forecast.summary();
  }

  public int temperature() {
    return forecast.temperature();
  }
}
