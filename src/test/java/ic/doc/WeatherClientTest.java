package ic.doc;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WeatherClientTest {

  @Test
  public void weatherClientReturnsSummaryStringAndTempInt() {

    WeatherClient weatherClient = new WeatherClient("LONDON", "FRIDAY");

    assertThat(weatherClient.summary().isEmpty(), is(false));
    assertThat(weatherClient.temperature(), is(instanceOf(Integer.class)));
  }

  @Test
  public void weatherClientCachesData() {

    WeatherClient weatherClient = new WeatherClient("BIRMINGHAM", "MONDAY");


  }
}
