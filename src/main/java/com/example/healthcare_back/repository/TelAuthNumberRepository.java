package com.example.healthcare_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.healthcare_back.entity.TelAuthNumberEntity;

@Repository
public interface TelAuthNumberRepository extends JpaRepository<TelAuthNumberEntity, String> {
    
    boolean existsByTelNumberAndAuthNumber(String telNumber, String authNumber);

    boolean existsByTelNumber(String telNumber);

}
