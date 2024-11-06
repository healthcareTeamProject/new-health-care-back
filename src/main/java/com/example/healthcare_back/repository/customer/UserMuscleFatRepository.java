package com.example.healthcare_back.repository.customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.entity.customer.CustomerEntity;
import com.example.healthcare_back.entity.customer.UserMuscleFatEntity;

@Repository
public interface UserMuscleFatRepository extends JpaRepository<UserMuscleFatEntity, Integer> {
    
    List<UserMuscleFatEntity> findByUserIdOrderByUserMuscleFatNumberAsc(String userId);

    CustomerEntity findByUserId(String userId);

}