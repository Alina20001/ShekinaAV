package com.alina.shekina.repository;

import com.alina.shekina.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User getByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsById(long id);
}
