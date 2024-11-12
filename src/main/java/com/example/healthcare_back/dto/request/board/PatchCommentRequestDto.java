package com.example.healthcare_back.dto.request.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 댓글 수정 Request Body DTO

@Getter
@Setter
@NoArgsConstructor
public class PatchCommentRequestDto {
    
    private String commentContents;

}
