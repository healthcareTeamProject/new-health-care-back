package com.example.healthcare_back.entity.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 전화번호 인증 엔터티

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="telAuthNumber")
@Table(name="tel_auth_number")
public class TelAuthNumberEntity {
    @Id
    private String telNumber;
    private String authNumber;
}
