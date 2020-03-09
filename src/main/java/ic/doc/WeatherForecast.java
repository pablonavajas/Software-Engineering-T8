package ic.doc;

import static java.lang.System.currentTimeMillis;

public class WeatherForecast {

  private int temp;
  private String description;
  private double timeStamp;

  public WeatherForecast(int temp, String description) {
    this.temp = temp;
    this.description = description;
    this.timeStamp = currentTimeMillis();
  }

  public int getTemp() {
    return temp;
  }

  public String getDescription() {
    return description;
  }

  public double getTimeStamp() {
    return timeStamp;
  }
}

