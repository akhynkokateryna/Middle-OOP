package company;

public class BrandfetchParser {
    Company getData(String domain){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.peopledatalabs.com/v5/company/enrich?website="+website+"&pretty=false")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("X-API-Key", "89f0e03c115b86c891ee331636dc2f5ca76093fa430595692a41a0e311c1ff23")
                .build();

        Response response = client.newCall(request).execute();
    }
}
