package company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@RestController
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(path = "/api/company")
    public List<Company> getCompanies() {
        return companyService.getCompanies();
    }

    @RequestMapping(path = "/api/company/{domen}")
    public Optional<Company> getCompany(@PathVariable("domen") String domen) {
        return companyService.getCompany(domen);
    }

    @PostMapping()
    public void addCompany(@RequestBody Company company) {
        companyService.addCompany(company);
    }
}
