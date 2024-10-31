package com.example.healthcare_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.entity.UserThreeMajorLiftEntity;

@Repository
public interface UserThreeMajorLiftRepository extends JpaRepository<UserThreeMajorLiftEntity, Integer>  {
    
    List<UserThreeMajorLiftEntity> findByOrderByUserThreeMajorLiftNumberDesc();
    UserThreeMajorLiftEntity findByUserId(String userId);

}
