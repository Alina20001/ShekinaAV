package com.alina.shekina.repository;

import com.alina.shekina.entity.Services;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends CrudRepository<Services,Long> {
}
