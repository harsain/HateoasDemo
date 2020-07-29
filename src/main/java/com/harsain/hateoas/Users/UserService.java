package com.harsain.hateoas.Users;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAll(){
        return IteratorUtils.toList(userRepository.findAll().iterator());
    }

    public User getUserById(int id){
        return userRepository.findOne(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
