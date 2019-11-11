package network;

import jdk.nashorn.internal.ir.debug.JSONWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import com.google.gson.*;
import org.json.*;

public class ReadWeather {
    String apikey = "5a47f7a7ab7073c2609188e157b72d69";
    String vancouverweather = "https://api.openweathermap.org/data/2.5/weather?id=6173331&units=metric&APPID=";
    String theURL = vancouverweather + apikey;
    BufferedReader br = null;

    public ReadWeather() {
        try {
            getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getData() throws IOException {  // CPSC210 Deliverable 10 Edx Page
        try {
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line);
                //sb.append(System.lineSeparator());
            }

            JSONObject jsonObject = new JSONObject(sb.toString());

            System.out.println("Location: " + jsonObject.getString("name"));

            printWeather(jsonObject);

            printTemp(jsonObject);
            System.out.println("");
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    private static void printWeather(JSONObject jsonObject) {
        JSONArray weatherArray = jsonObject.getJSONArray("weather");
        JSONObject weatherObject = weatherArray.getJSONObject(0);
        String weather = weatherObject.getString("main");
        String desc = weatherObject.getString("description");
        System.out.println("Weather: " +  weather);
        System.out.println("Description: " + desc);
    }

    private static void printTemp(JSONObject jsonObject) {
        JSONObject temp = jsonObject.getJSONObject("main");
        int mintemp = temp.getInt("temp_min");
        int maxtemp = temp.getInt("temp");
        System.out.println("Temperature: " + mintemp + " to " + maxtemp);
    }

}
