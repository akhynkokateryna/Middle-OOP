package company;

import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    public List<Company> getCompany(String domain) throws IOException {
        if (domain.equals("all")) {
            return companyRepository.findAll();
        }

        if(!companyRepository.findCompanyByDomain(domain).isEmpty()) {
            return companyRepository.findCompanyByDomain(domain);
        }

        BrandfetchParser brandfetchParser = new BrandfetchParser();
        PDLReader pdlReader = new PDLReader();
        CURLParser curlParser = new CURLParser();
        CompanyMerger companyMerger = new CompanyMerger();

        List<Company> companies = new ArrayList<>();
        companies.add(brandfetchParser.getData(domain));
        companies.add(pdlReader.getData(domain));
        companies.add(curlParser.getCURLedObject(domain));

        JSONObject jsonObject = companyMerger.mergeIntoJSON(companies);
        Company res_company =  Company.builder()
                .domain(jsonObject.getString("domain"))
                .name(jsonObject.getString("name"))
                .address(jsonObject.getString("address"))
                .facebook(jsonObject.getString("facebook"))
                .twitter(jsonObject.getString("twitter"))
                .employees(jsonObject.getString("employees"))
                .icon(jsonObject.getString("icon"))
                .logo(jsonObject.getString("logo"))
                .build();

        companyRepository.save(res_company);
        return List.of(res_company);
    }


    public void addCompany(Company company) {
        List<Company> neededCompany = companyRepository.findCompanyByDomain(company.getDomain());
        if (!neededCompany.isEmpty()) {
            companyRepository.deleteById(neededCompany.get(0).getId());
        }
        companyRepository.save(company);
    }

//    @SneakyThrows
//    public static void main(String[] args) {
//        String domain = "ucu.edu.ua";
//        BrandfetchParser brandfetchParser = new BrandfetchParser();
//        PDLReader pdlReader = new PDLReader();
//        CompanyMerger companyMerger = new CompanyMerger();
//
//        List<Company> companies = new ArrayList<>();
//        companies.add(brandfetchParser.getData(domain));
//        companies.add(pdlReader.getData(domain));
//
//        JSONObject jsonObject = companyMerger.mergeIntoJSON(companies);
//        Company res_company =  Company.builder()
//                .domain(jsonObject.getString("domain"))
//                .name(jsonObject.getString("name"))
//                .address(jsonObject.getString("address"))
//                .facebook(jsonObject.getString("facebook"))
//                .twitter(jsonObject.getString("twitter"))
//                .employees(jsonObject.getString("employees"))
//                .icon(jsonObject.getString("icon"))
//                .logo(jsonObject.getString("logo"))
//                .build();
//        System.out.println(res_company);
//
//    }
}
