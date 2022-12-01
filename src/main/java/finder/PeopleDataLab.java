package finder;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import company.Company;

import java.io.IOException;

public class PeopleDataLab {
    void getData(String website, Company company) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.peopledatalabs.com/v5/company/enrich?website="+website+"&pretty=false")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("X-API-Key", "89f0e03c115b86c891ee331636dc2f5ca76093fa430595692a41a0e311c1ff23")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.message());
    }
}
