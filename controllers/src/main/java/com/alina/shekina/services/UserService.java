package com.alina.shekina.services;

import com.alina.shekina.entity.User;
import com.alina.shekina.repository.UserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Setter(onMethod_ = @Autowired)
public class UserService {

    private UserRepository userRepository;

    @Transactional
    public void updateUser(Long id,String surname, String name, String country,  String phone){
        User user = userRepository.findById(id).get();
        user.setName(name);
        user.setCountry(country);
        user.setPhone(phone);
        user.setSurname(surname);
        userRepository.save(user);
    }
}
