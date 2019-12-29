package cz.pia.cagy.accountingApp.service;

import cz.pia.cagy.accountingApp.model.User;
import cz.pia.cagy.accountingApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService
{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers()
    {
        List<User> retVal = new LinkedList<>();
        for (User user : this.userRepository.findAll()) {
            retVal.add(user);
        }
        return Collections.unmodifiableList(retVal);
    }

    @Override
    public User getUserById(long id)
    {
        return this.userRepository.findById(id);
    }

    @Override
    public void saveUser(User user)
    {
        this.userRepository.save(user);
    }

}
