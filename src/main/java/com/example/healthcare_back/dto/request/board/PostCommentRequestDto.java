package com.example.healthcare_back.dto.request.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 댓글 작성 Request Body DTO

@Getter
@Setter
@NoArgsConstructor
public class PostCommentRequestDto {
    private String commentContents;
}
