package cz.pia.cagy.accountingApp.repository;

import cz.pia.cagy.accountingApp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface repository for role.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{
}
