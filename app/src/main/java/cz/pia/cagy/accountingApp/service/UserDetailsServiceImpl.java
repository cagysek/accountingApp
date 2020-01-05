package cz.pia.cagy.accountingApp.service;

import cz.pia.cagy.accountingApp.model.User;
import cz.pia.cagy.accountingApp.repository.UserRepository;
import cz.pia.cagy.accountingApp.model.security.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * The type User details service.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findByUsername(username);
        if (user != null)
        {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

            // user can have only just one role
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));

            return new LoggedUser(user.getUsername(), user.getPassword(), grantedAuthorities, user.getId());
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
