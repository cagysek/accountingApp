package cz.pia.cagy.accountingApp.service;

import cz.pia.cagy.accountingApp.model.Company;

import java.util.List;


public interface CompanyService
{
    List<Company> getCompanies();

    Company getCompanyById(long companyId);

    void saveCompany(Company company);

    void deleteCompanyById(long companyId);
}
