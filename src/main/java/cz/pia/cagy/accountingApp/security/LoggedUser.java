package cz.pia.cagy.accountingApp.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


public class LoggedUser extends User
{
    private final long userId;

    public LoggedUser(String username, String password, Collection<? extends GrantedAuthority> authorities, long userId)
    {
        super(username, password, authorities);
        this.userId = userId;
    }

    public long getUserId()
    {
        return userId;
    }
}
