package com.hanteo.board.api.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponse {
    private Long id;
    private String name;
    private int boardNumber;
}
