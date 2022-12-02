package finder;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class PDLReader {
    public static void main(String[] args) throws IOException, JSONException {
        String API_KEY = "89f0e03c115b86c891ee331636dc2f5ca76093fa430595692a41a0e311c1ff23";
        String query = URLEncoder.encode("SELECT NAME FROM COMPANY WHERE WEBSITE='ucu.edu.ua'", StandardCharsets.UTF_8);
        URL url = new URL("https://api.peopledatalabs.com/v5/company/search?sql=" + query);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Api-Key", API_KEY);
        connection.connect();
        String text = new Scanner(connection.getInputStream()).useDelimiter("\\Z").next();
        JSONObject jsonObject = new JSONObject(text);
        System.out.println(jsonObject);
        System.out.println(jsonObject.getJSONArray("data").getJSONObject(0).getInt("employee_count"));
    }
}
