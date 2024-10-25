package com.example.healthcare_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.entity.UserMuscleFatEntity;

@Repository
public interface UserMuscleFatRepository extends JpaRepository<UserMuscleFatEntity, Double> {
    
    List<UserMuscleFatEntity> findByOrderByUserMuscleFatNumberDesc();

}
