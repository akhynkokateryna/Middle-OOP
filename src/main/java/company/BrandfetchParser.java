package company;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;

public class BrandfetchParser {
    @SneakyThrows
    Company getData(String domain){

        Company company = new Company();
            if(domain.startsWith("https://")) {domain.replace("https://", "");}
            if(domain.startsWith("http://")) {domain.replace("http://", "");}

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.brandfetch.io/v2/brands/"+domain)
                .addHeader("Authorization", "Bearer dmNkRe6Qk/Hbyq6WGBfoYL2kkGh0r3nlClHhIaZLR7k=")
                .get()
                .build();
        Response response = client.newCall(request).execute();

        String jsonString = response.body().string();
        JSONObject obj = new JSONObject(jsonString);

            if(obj.has("name") && !obj.isNull("name")) {
            company.setName(obj.getString("name"));
        }


        JSONArray links = obj.getJSONArray("links");
        for (int i = 0; i < links.length(); i++) {
            if(links.getJSONObject(i).getString("name").equals("twitter")) {
                company.setTwitter(links.getJSONObject(i).getString("url"));
            }
            if(links.getJSONObject(i).getString("name").equals("facebook")) {
                company.setFacebook(links.getJSONObject(i).getString("url"));
            }
        }
        JSONArray logos = obj.getJSONArray("logos");
        JSONArray formats;
        for (int i = 0; i < logos.length(); i++) {
            System.out.println(logos.getJSONObject(i).toString());
            if(logos.getJSONObject(i).getString("type").equals("icon")) {
                formats = logos.getJSONObject(i).getJSONArray("formats");
                company.setIcon(formats.getJSONObject(0).getString("src"));

            }
            if(logos.getJSONObject(i).getString("type").equals("logo")) {
                formats = logos.getJSONObject(i).getJSONArray("formats");
                company.setLogo(formats.getJSONObject(0).getString("src"));
            }
        }
        return company;
    }
}
