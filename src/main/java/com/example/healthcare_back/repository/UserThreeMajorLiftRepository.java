package com.example.healthcare_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.repository.resultSet.UserThreeMajorLiftResultSet;

@Repository
public interface UserThreeMajorLiftRepository extends JpaRepository<UserThreeMajorLiftResultSet, Integer>  {
    
    List<UserThreeMajorLiftResultSet> findByOrderByUserThreeMajorLiftNumberDesc();
    UserThreeMajorLiftResultSet findByUserId(String userId);

}
