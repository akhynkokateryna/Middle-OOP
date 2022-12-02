package finder;

import company.Company;
import org.json.JSONException;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, JSONException {
        Company company=new Company();
        PDLReader pdl= new PDLReader();
        pdl.getData("ucu.edu.ua", company);
        System.out.println(company.toString());
    }
}
