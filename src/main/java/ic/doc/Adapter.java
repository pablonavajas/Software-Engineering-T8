package ic.doc;

import com.weather.Day;
import com.weather.Region;

public interface Adapter {

  Day convertDay(Day weekDay);

  Region convertRegion(Region location);
}
