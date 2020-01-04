package cz.pia.cagy.accountingApp.repository;

import cz.pia.cagy.accountingApp.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface repository for address.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long>
{
}
