package cz.pia.cagy.accountingApp.service;

import cz.pia.cagy.accountingApp.model.User;
import cz.pia.cagy.accountingApp.repository.UserRepository;
import cz.pia.cagy.accountingApp.security.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        System.out.println(username);
        User user = userRepository.findByUsername(username);
        System.out.println(user.getFirstName() + user.getPassword() + user.getId());
        if (user != null)
        {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

            // user can have only just one role
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getName()));


            return new LoggedUser(user.getUsername(), user.getPassword(), grantedAuthorities, user.getId());
        }

        return null;
    }
}
