import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherNetworking {

    private String baseUrl;
    private String apiKey;

    public WeatherNetworking() {
        baseUrl = "http://api.weatherapi.com/v1";
        apiKey = "8823c95dd63e46079a3170028221805";
    }

    public String makeAPICallForForecast(String zipCode, int days) {
        String endPoint = "/forecast.json";
        String url = baseUrl + endPoint + "?q=" + zipCode + "&key=" + apiKey + "&days=" + days;

        try {
            URI myUri = URI.create(url); // creates a URI object from the url string
            HttpRequest request = HttpRequest.newBuilder().uri(myUri).build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String makeAPICallForSpace(String loc, String date) {
        String endPoint = "/astronomy.json";
        String url = baseUrl + endPoint + "?q=" + loc + "&key=" + apiKey + "&dt=" + date;
        try {
            URI myUri = URI.create(url); // creates a URI object from the url string
            HttpRequest request = HttpRequest.newBuilder().uri(myUri).build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void parceSpace(String json)
    {
        JSONObject jsonObj = new JSONObject(json);
        JSONObject astronomyObj = jsonObj.getJSONObject("astronomy");
        JSONObject astroObj = astronomyObj.getJSONObject("astro");
        String sunset = astroObj.getString("sunset");
        String sunrise = astroObj.getString("sunrise");
        System.out.println("Sunrise: " + sunrise +", Sunset: " + sunset);

    }

    public void parseForecast(String json)
    {
        JSONObject jsonObj = new JSONObject(json);
        JSONObject forecastObj = jsonObj.getJSONObject("forecast");
        JSONArray forecaseArr = forecastObj.getJSONArray("forecastday");

        for (int i = 0; i < forecaseArr.length(); i++)
        {
            JSONObject forecast = forecaseArr.getJSONObject(i);
            JSONObject dayObj = forecast.getJSONObject("day");
            double minTemp = dayObj.getDouble("mintemp_f");
            double maxTemp = dayObj.getDouble("maxtemp_f");
            System.out.println("Min temp: " + minTemp + ", Max temp = " + maxTemp);
        }
    }
}
