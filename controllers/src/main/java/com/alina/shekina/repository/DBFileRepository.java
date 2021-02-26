package com.alina.shekina.repository;

import com.alina.shekina.entity.DBFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBFileRepository extends CrudRepository<DBFile,Long> {
}
