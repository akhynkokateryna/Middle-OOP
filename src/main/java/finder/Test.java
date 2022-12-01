package finder;

import company.Company;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        Company company=new Company();
        PeopleDataLab pdl= new PeopleDataLab();
        pdl.getData("ucu.edu.ua", company);
    }
}
