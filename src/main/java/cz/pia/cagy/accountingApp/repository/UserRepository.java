package cz.pia.cagy.accountingApp.repository;

import cz.pia.cagy.accountingApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface repository for user.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    /**
     * Find user by username.
     *
     * @param username the username
     * @return the user
     */
    User findByUsername(String username);

    /**
     * Find user by id.
     *
     * @param id the id
     * @return the user
     */
    User findById(long id);

}
