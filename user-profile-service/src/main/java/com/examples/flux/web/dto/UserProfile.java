package com.examples.flux.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    private String fio;
    private String job;
    private String quote;
    private List<ImageDto> imageDtos;
}
