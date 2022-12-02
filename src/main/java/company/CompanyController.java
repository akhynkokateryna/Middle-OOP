package company;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @SneakyThrows
    @RequestMapping(path = "/api/company/{domain}")
    public List<Company> getCompany(@PathVariable("domain") String domain) {
        return companyService.getCompany(domain);
    }

    @PostMapping(path = "/api/company")
    public void addCompany(@RequestBody Company company) {
        companyService.addCompany(company);
    }
}
