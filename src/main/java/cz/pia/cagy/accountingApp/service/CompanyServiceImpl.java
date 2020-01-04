package cz.pia.cagy.accountingApp.service;

import cz.pia.cagy.accountingApp.model.Company;
import cz.pia.cagy.accountingApp.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Company service.
 */
@Service
public class CompanyServiceImpl implements CompanyService
{

    private CompanyRepository companyRepository;

    /**
     * Instantiates a new Company service.
     *
     * @param companyRepository the company repository
     */
    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository)
    {

        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getCompanies()
    {
        return this.companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(long companyId)
    {
        return this.companyRepository.findById(companyId);
    }

    @Override
    public void saveCompany(Company company)
    {
        this.companyRepository.save(company);
    }

    @Override
    public void deleteCompanyById(long companyId)
    {
        this.companyRepository.deleteById(companyId);
    }


}
