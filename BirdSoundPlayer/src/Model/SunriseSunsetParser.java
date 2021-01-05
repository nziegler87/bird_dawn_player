package Model;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.Arrays;

public class SunriseSunsetParser {
    String JSONBody;
    ZonedDateTime sunrise;
    Map<String, String> time_data = new LinkedHashMap<>();
    List<String> timeFields = Arrays.asList("sunrise", "sunset", "solar_noon","day_length", "civil_twilight_begin",
            "civil_twilight_end", "nautical_twilight_begin", "nautical_twilight_end", "astronomical_twilight_begin",
            "astronomical_twilight_end");

    public SunriseSunsetParser(String json) {
        this.JSONBody = json;
        this.parseData();
        this.setSunrise();
    }

    /**
     * Parses the data from the json string and stores each field in a linked hash map parameter.
     */
    private void parseData() {
        String [] parsedData = this.JSONBody.split("\"");
        for (int i = 0 ; i < parsedData.length ; i++ ) {
            if (timeFields.contains(parsedData[i])) {
                time_data.put(parsedData[i], parsedData[i + 2]);
            }
        }
    }

    private void setSunrise() {
        String temp_sunrise = time_data.get("sunrise");
        this.sunrise = ZonedDateTime.parse(temp_sunrise, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public ZonedDateTime getSunrise() throws IllegalStateException {
        if (this.sunrise == null) {
            throw new IllegalStateException("Sunrise data not parsed. First parse the provided Json object and set sunrise.");
        }
        return this.sunrise;
    }
}
