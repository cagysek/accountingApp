package cz.pia.cagy.accountingApp.service;

import cz.pia.cagy.accountingApp.model.Role;

import java.util.List;

/**
 * The interface Role service.
 */
public interface RoleService
{
    /**
     * Gets roles.
     *
     * @return the roles
     */
    List<Role> getRoles();
}
