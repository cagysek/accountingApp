package cz.pia.cagy.accountingApp.service;

import cz.pia.cagy.accountingApp.model.User;

import java.util.List;

/**
 * The interface User service.
 */
public interface UserService
{
    /**
     * Gets users.
     *
     * @return the users
     */
    List<User> getUsers();

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     */
    User getUserById(long id);

    /**
     * Save user.
     *
     * @param user the user
     */
    void saveUser(User user);

    /**
     * Delete user by id.
     *
     * @param id the id
     */
    void deleteUserById(long id);

    /**
     * Change user password.
     *
     * @param id          the id
     * @param newPassword the new password
     */
    void changeUserPassword(long id, String newPassword);

}
