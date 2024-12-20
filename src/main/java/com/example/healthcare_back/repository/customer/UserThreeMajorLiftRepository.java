package com.example.healthcare_back.repository.customer;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.entity.customer.UserThreeMajorLiftEntity;

@Repository
public interface UserThreeMajorLiftRepository extends JpaRepository<UserThreeMajorLiftEntity, Integer>  {

    List<UserThreeMajorLiftEntity> findByUserIdOrderByUserThreeMajorLiftNumberAsc(String userId);
    UserThreeMajorLiftEntity findByUserId(String userId);
}

