package com.example.healthcare_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
    
    boolean existsByUserId(String userId);
    boolean existsByTelNumber(String telNumber);
    boolean existsByNickname(String nickname);
    
    CustomerEntity findByUserId(String userId);
    CustomerEntity findBySnsIdAndJoinPath(String sns, String joinPath);

}
