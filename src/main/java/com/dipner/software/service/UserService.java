package com.dipner.software.service;

import com.dipner.software.beans.LoginUsers;
import com.dipner.software.beans.Users;
import com.dipner.software.repository.LoginRepository;
import com.dipner.software.repository.UserRepository;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository usersRepository;

    @Autowired
    public UserService(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void registerUser(Users user) {
        // Save user to the database
        usersRepository.save(user);
    }
}
