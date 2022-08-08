package com.robson.os.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.robson.os.model.OS;

@Repository
public interface OSRepository extends JpaRepository<OS, Integer>{

}
