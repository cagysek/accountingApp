package cz.pia.cagy.accountingApp.repository;

import cz.pia.cagy.accountingApp.model.Company;
import cz.pia.cagy.accountingApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>
{
    Company findById(long id);

}
