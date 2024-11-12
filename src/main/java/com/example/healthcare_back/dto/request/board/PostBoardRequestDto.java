package com.example.healthcare_back.dto.request.board;

<<<<<<< HEAD
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 게시물 작성 Request Body DTO

=======
import java.math.BigDecimal;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 게시물 작성 Request Body DTO

>>>>>>> 59ec9a791fce95275047e79dddde2077520ea816
@Getter
@Setter
@NoArgsConstructor
public class PostBoardRequestDto {
    
<<<<<<< HEAD
    @NotBlank
    private String boardTitle;
    @NotBlank
    private String nickname;
    @NotBlank
    private String boardCategory;
    @NotBlank
    private String boardTag;
    @NotBlank
    private String boardContents;
    private String youtubeVideoLink;
    private String boardFileContents;
    private String mapLat;
    private String mapLng;

}
=======
    private String boardTitle;
    
    private String userId;
    
    private String boardCategory;
    
    private String boardTag;
    
    private String boardContents;
    
    private String youtubeVideoLink;
    
    private String boardFileContents;
    @Column(precision = 9, scale = 6)
    private BigDecimal mapLat;
    @Column(precision = 9, scale = 6)
    private BigDecimal mapLng;
}

>>>>>>> 59ec9a791fce95275047e79dddde2077520ea816
