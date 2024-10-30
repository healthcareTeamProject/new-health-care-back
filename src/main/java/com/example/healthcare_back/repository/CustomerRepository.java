package com.example.healthcare_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
    boolean existsByUserId(String userId);
    boolean existsByTelNumber(String telNumber);
    boolean existsByNickname(String nickname);
    boolean existsByProfileImage(String profileImage);
    boolean existsByPersonalGoal(String personalGoal);
    
    CustomerEntity findByUserId(String userId);
    CustomerEntity findBySnsIdAndJoinPath(String sns, String joinPath);
    List<CustomerEntity> findByNicknameContaining(String nicknamePart);
}
