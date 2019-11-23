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
    public static JSONObject jsonObject = null;

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

            jsonObject = new JSONObject(sb.toString());

            System.out.println("Location: " + getLocation(jsonObject));

            printWeather(getWeather(jsonObject), getDescription(jsonObject));

            printTemp(getMinTemp(jsonObject), getMaxTemp(jsonObject));
            System.out.println("");
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public static void printWeather(String weather, String desc) {
        System.out.println("Weather: " + weather + "\n" + "Description: " + desc);
    }

    public static String getLocation(JSONObject jsonObject) {
        return jsonObject.getString("name");
    }

    public static String getWeather(JSONObject jsonObject) {
        JSONArray weatherArray = jsonObject.getJSONArray("weather");
        JSONObject weatherObject = weatherArray.getJSONObject(0);
        String weather = weatherObject.getString("main");
        return weather;
    }

    public static String getDescription(JSONObject jsonObject) {
        JSONArray weatherArray = jsonObject.getJSONArray("weather");
        JSONObject weatherObject = weatherArray.getJSONObject(0);
        String desc = weatherObject.getString("description");
        return desc;
    }

    public static int getMinTemp(JSONObject jsonObject) {
        JSONObject temp = jsonObject.getJSONObject("main");
        int mintemp = temp.getInt("temp_min");
        return mintemp;
    }

    public static int getMaxTemp(JSONObject jsonObject) {
        JSONObject temp = jsonObject.getJSONObject("main");
        int maxtemp = temp.getInt("temp");
        return maxtemp;
    }

    public static void printTemp(int mintemp, int maxtemp) {
        System.out.println("Temperature: " + mintemp + " to " + maxtemp);
    }

}
