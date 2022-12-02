package company;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parsers.BrandfetchParser;
import parsers.CURLParser;
import parsers.PDLReader;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    public List<Company> getCompany(String domain){
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
        companies.add(curlParser.getData(domain));

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
}
