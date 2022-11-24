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


//    to change
    public List<Company> getCompany() {
        return companyRepository.findAll();
    }


//    to change
    public void addCompany(Company company) {
        if (companyRepository.findAppUserByName(company.getName()).isEmpty()) {
            companyRepository.save(company);
        }
    }
}
