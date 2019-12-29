package cz.pia.cagy.accountingApp.service;

import cz.pia.cagy.accountingApp.model.Role;
import cz.pia.cagy.accountingApp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService
{
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {

        this.roleRepository = roleRepository;
    }

    public List<Role> getRoles()
    {
        return this.roleRepository.findAll();
    }
}
