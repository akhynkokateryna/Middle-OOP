package company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    public List<Company> getCompany(String domain) {
        if (domain.equals("all")) {
            return companyRepository.findAll();
        } else {
            return companyRepository.findCompanyByDomain(domain);
        }
    }


    public void addCompany(Company company) {
        List<Company> neededCompany = companyRepository.findCompanyByDomain(company.getDomain());
        if (!neededCompany.isEmpty()) {
            companyRepository.deleteById(neededCompany.get(0).getId());
        }
        companyRepository.save(company);
    }
}
