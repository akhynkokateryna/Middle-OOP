package company;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CompanyMerger {
    CompanyMerger instance = null;

    private CompanyMerger getInstance() {
        if (instance == null) {
            return new CompanyMerger();
        }
        return instance;
    }

    public JSONObject mergeIntoJSON(List<Company> toScrape) {
        JSONObject output = new JSONObject();

        HashMap<String, List<String>> valuesScraped = new HashMap<String, List<String>>();

        String[] tokenList = {"name", "domain", "twitter", "facebook", "logo", "icon", "employees", "address"};

        for (String token:
             tokenList) {
            valuesScraped.put(token, new ArrayList<>());
        }

        Field[] props = Company.class.getDeclaredFields();

        for (Field yo : props) {
            for (int i = 0; i < toScrape.size(); i++) {
                try {
                    String attributeValue = String.valueOf(yo.get(toScrape.get(i)));
                    Object attributeName = yo.getName();
                    if (!valuesScraped.get(attributeName).contains(attributeValue) && !attributeValue.equals("null")) {
                        valuesScraped.get(attributeName).add(attributeValue);
                    }
                }
                catch (JSONException e) {

                }
                catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        for (String token : tokenList) {
            try {
                String toWrite = valuesScraped.get(token).get(0);
                output.put(token, toWrite);
            }
            catch (IndexOutOfBoundsException e) {
                output.put(token, "");
            }
        }

        return output;
    }

}
