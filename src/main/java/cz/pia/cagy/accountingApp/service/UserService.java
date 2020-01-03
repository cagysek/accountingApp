package cz.pia.cagy.accountingApp.service;

import cz.pia.cagy.accountingApp.model.User;

import java.util.List;

public interface UserService
{
    List<User> getUsers();

    User getUserById(long id);

    void saveUser(User user);

    void deleteUserById(long id);

    void changeUserPassword(long id, String newPassword);

}
