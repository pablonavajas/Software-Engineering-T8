package ic.doc;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WeatherClientTest {

  @Test
  public void weatherClientReturnsSummaryStringAndTempInt() {

    WeatherClient weatherClient = new WeatherClient();

    weatherClient.check("LONDON", "FRIDAY");

    assertThat(weatherClient.summary().isEmpty(), is(false));
    assertThat(weatherClient.temperature(), is(instanceOf(Integer.class)));
  }

  @Test
  public void weatherClientCachesData() {

    WeatherClient weatherClient = new WeatherClient();

    weatherClient.check("BIRMINGHAM", "TUESDAY");

    assertThat(weatherClient.dataCached(), is(false));
    assertThat(weatherClient.summary().isEmpty(), is(false));
    assertThat(weatherClient.temperature(), is(instanceOf(Integer.class)));


    weatherClient.check("BIRMINGHAM", "TUESDAY");

    assertThat(weatherClient.dataCached(), is(true));
    assertThat(weatherClient.summary().isEmpty(), is(false));
    assertThat(weatherClient.temperature(), is(instanceOf(Integer.class)));
  }

  @Test
  public void weatherClientCachesDataUpToMaxSize() {

    WeatherClient weatherClient = new WeatherClient(1);

    weatherClient.check("BIRMINGHAM", "TUESDAY");
    assertThat(weatherClient.dataCached(), is(false));

    //weatherClient.check("LONDON", "TUESDAY");
    //assertThat(weatherClient.dataCached(), is(false));

    weatherClient.check("BIRMINGHAM", "TUESDAY");
    assertThat(weatherClient.dataCached(), is(true));

    weatherClient.check("EDINBURGH", "TUESDAY");
    assertThat(weatherClient.dataCached(), is(false));

    weatherClient.check("BIRMINGHAM", "TUESDAY");
    assertThat(weatherClient.dataCached(), is(false));
  }
}
