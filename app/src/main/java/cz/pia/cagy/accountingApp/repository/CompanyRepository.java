package cz.pia.cagy.accountingApp.repository;

import cz.pia.cagy.accountingApp.model.Company;
import cz.pia.cagy.accountingApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface repository for company.
 */
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>
{
    /**
     * Find company by id.
     *
     * @param id the id
     * @return the company
     */
    Company findById(long id);

}
