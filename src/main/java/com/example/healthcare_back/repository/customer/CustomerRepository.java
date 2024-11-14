package com.example.healthcare_back.repository.customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.entity.customer.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
    
    boolean existsByUserId(String userId);
    boolean existsByTelNumber(String telNumber);
    boolean existsByNickname(String nickname);
    boolean existsByProfileImage(String profileImage);
    boolean existsByPersonalGoals(String personalGoals);
    
    CustomerEntity findByUserId(String userId);
    List<CustomerEntity> findAll();
    CustomerEntity findBySnsIdAndJoinPath(String sns, String joinPath);

}
