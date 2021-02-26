package com.alina.shekina.repository;

import com.alina.shekina.entity.Residence;
import com.alina.shekina.entity.Status;
import com.alina.shekina.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResidenceRepository extends CrudRepository<Residence,Long> {

    @Query("select r from Residence r where r.user=?1 and r.status='ACTIVE'")
    Optional<Residence> findActiveByUser(User user);

    boolean existsByUserAndStatus(User user, Status status);

    @Query("select r from Residence r where r.status = 'ACTIVE'")
    List<Residence> findAllActive();

    List<Residence> findAllByUser(User user);
}
