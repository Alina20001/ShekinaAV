package com.alina.shekina.userDetails;

import com.alina.shekina.entity.User;
import com.alina.shekina.repository.UserRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Slf4j
@Transactional
@Service
@Setter(onMethod_ = @Autowired)
public class UserServiceImpl{

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    @Transactional
    public void saveUser(String email, String password, String country, String name, String phone, String surname) throws Exception {
        if (!userRepository.existsByEmail(email)){
            User user = new User();
            user.setCountry(country);
            user.setName(name);
            user.setPhone(phone);
            user.setSurname(surname);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
        } else {
            throw new Exception("user exists");
        }

    }

}
