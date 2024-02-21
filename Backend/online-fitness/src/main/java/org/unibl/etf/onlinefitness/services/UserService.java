package org.unibl.etf.onlinefitness.services;

import org.apache.catalina.User;
import org.unibl.etf.onlinefitness.repositories.UserRepository;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
}
