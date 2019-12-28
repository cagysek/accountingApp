package cz.pia.cagy.accountingApp.repository;

import cz.pia.cagy.accountingApp.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long>
{
}
