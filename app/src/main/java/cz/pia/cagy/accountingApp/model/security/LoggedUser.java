package cz.pia.cagy.accountingApp.model.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


/**
 * Object which extends from security.user
 * Object against security.user contains additional info logged user ID.
 */
public class LoggedUser extends User
{
    private final long userId;

    /**
     * Instantiates a new Logged user.
     *
     * @param username    the username
     * @param password    the password
     * @param authorities the authorities
     * @param userId      the user id
     */
    public LoggedUser(String username, String password, Collection<? extends GrantedAuthority> authorities, long userId)
    {
        super(username, password, authorities);
        this.userId = userId;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public long getUserId()
    {
        return userId;
    }
}
