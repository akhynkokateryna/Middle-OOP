package company;

import company.Company;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class PDLReader {
    public void getData(String website, Company company) throws IOException, JSONException {
        String API_KEY = "89f0e03c115b86c891ee331636dc2f5ca76093fa430595692a41a0e311c1ff23";
        String query = URLEncoder.encode("SELECT NAME FROM COMPANY WHERE WEBSITE='"+website+"'", StandardCharsets.UTF_8);
        URL url = new URL("https://api.peopledatalabs.com/v5/company/search?sql=" + query);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Api-Key", API_KEY);
        connection.connect();
        String text = new Scanner(connection.getInputStream()).useDelimiter("\\Z").next();
        JSONObject jsonObject = new JSONObject(text);
        if(company.getName()==null){
            company.setName(jsonObject.getJSONArray("data").getJSONObject(0).getString("name"));
        }
        if (company.getFacebook()==null){
            company.setFacebook(jsonObject.getJSONArray("data").getJSONObject(0).getString("facebook_url"));
        }
        if (company.getTwitter()==null){
            company.setTwitter(jsonObject.getJSONArray("data").getJSONObject(0).getString("twitter_url"));
        }
        if (company.getEmployees()==null){
            company.setEmployees(jsonObject.getJSONArray("data").getJSONObject(0).getString("size"));
        }
        if (company.getAddress()==null){
            company.setAddress(jsonObject.getJSONArray("data").getJSONObject(0).
                    getJSONObject("location").getString("street_address"));
        }
    }
}
