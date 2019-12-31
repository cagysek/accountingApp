package cz.pia.cagy.accountingApp.service;

import cz.pia.cagy.accountingApp.model.Company;
import cz.pia.cagy.accountingApp.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService
{

    private CompanyRepository companyRepository;

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
