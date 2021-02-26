package com.alina.shekina.services;

import com.alina.shekina.entity.DBFile;
import com.alina.shekina.repository.DBFileRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Setter(onMethod_ = @Autowired)
public class DBFileService {

    private DBFileRepository fileRepository;

    @Transactional
    public void save(DBFile dbFile){
        fileRepository.save(dbFile);
    }
}
