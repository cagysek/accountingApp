package cz.pia.cagy.accountingApp.service;

import cz.pia.cagy.accountingApp.model.Company;

import java.util.List;


/**
 * The interface Company service.
 */
public interface CompanyService
{
    /**
     * Gets companies.
     *
     * @return the companies
     */
    List<Company> getCompanies();

    /**
     * Gets company by id.
     *
     * @param companyId the company id
     * @return the company by id
     */
    Company getCompanyById(long companyId);

    /**
     * Save company.
     *
     * @param company the company
     */
    void saveCompany(Company company);

    /**
     * Delete company by id.
     *
     * @param companyId the company id
     */
    void deleteCompanyById(long companyId);
}
