package com.example.healthcare_back.dto.request.board;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchBoardRequestDto {
    
    @NotBlank
    private String boardTitle;
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
