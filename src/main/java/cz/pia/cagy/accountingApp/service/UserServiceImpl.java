package cz.pia.cagy.accountingApp.service;

import cz.pia.cagy.accountingApp.model.User;
import cz.pia.cagy.accountingApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService
{

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
        if (user.getPassword() != null)
        {
            user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        }

        this.userRepository.save(user);
    }

    @Override
    public void deleteUserById(long userId)
    {
        this.userRepository.deleteById(userId);
    }

}
