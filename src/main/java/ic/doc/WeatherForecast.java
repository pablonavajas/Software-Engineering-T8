package ic.doc;

public class WeatherForecast {

  private int temp;
  private String description;
  private double timeStamp;

  public WeatherForecast(int temp, String description) {
    this.temp = temp;
    this.description = description;
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

  public void setTimeStamp(double time) {
    this.timeStamp = time;
  }
}

