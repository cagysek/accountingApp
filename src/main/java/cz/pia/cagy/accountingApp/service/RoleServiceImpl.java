package cz.pia.cagy.accountingApp.service;

import cz.pia.cagy.accountingApp.model.Role;
import cz.pia.cagy.accountingApp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Role service.
 */
@Service
public class RoleServiceImpl implements RoleService
{
    private RoleRepository roleRepository;

    /**
     * Instantiates a new Role service.
     *
     * @param roleRepository the role repository
     */
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {

        this.roleRepository = roleRepository;
    }

    public List<Role> getRoles()
    {
        return this.roleRepository.findAll();
    }
}
