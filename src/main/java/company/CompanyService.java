package company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    public Optional<Company> getCompany(String domen) {
        return companyRepository.findAppUserByName(domen);
    }


//    to change
    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }
}
