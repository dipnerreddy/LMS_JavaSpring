package com.dipner.software.service;

import com.dipner.software.beans.LoginUsers;
import com.dipner.software.beans.Users;
import com.dipner.software.repository.LoginRepository;
import com.dipner.software.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public void loginUser(LoginUsers loginUsers) {
        // Save user to the database
        loginRepository.save(loginUsers);
    }

}
