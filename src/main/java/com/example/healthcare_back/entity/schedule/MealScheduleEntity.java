package com.example.healthcare_back.entity.schedule;


import java.time.LocalDateTime;
import java.util.List;

import com.example.healthcare_back.dto.request.schedule.PostMealScheduleRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="mealSchedule")
@Table(name="meal_Schedule")
public class MealScheduleEntity {
    
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mealScheduleNumber;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String mealTitle;

    @Column(nullable = false)
    private String mealMemo; // 여전히 String으로 유지

    @Column(nullable = false)
    private LocalDateTime mealScheduleStart;

    @Column(nullable = false)
    private LocalDateTime mealScheduleEnd;

    @OneToMany(mappedBy = "mealSchedule", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MealScheduleDetailEntity> mealScheduleDetails;

    // 생성자
    public MealScheduleEntity(PostMealScheduleRequestDto dto, String userId) throws JsonProcessingException {
        this.userId = userId;
        this.mealTitle = dto.getMealTitle();
        this.mealMemo = objectMapper.writeValueAsString(dto.getMealMemo()); // JSON으로 변환하여 저장
        this.mealScheduleStart = dto.getMealScheduleStart();
        this.mealScheduleEnd = dto.getMealScheduleEnd();
    }

    // mealMemo를 List<MealDetail>로 반환하는 헬퍼 메서드
    public List<PostMealScheduleRequestDto.MealDetail> getMealMemoAsList() throws JsonProcessingException {
        return objectMapper.readValue(mealMemo, new TypeReference<List<PostMealScheduleRequestDto.MealDetail>>() {});
    }

}
