package com.telusko.Quiz_Service.model;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserResponse {
    @Id
    private int id;
    private String response;
}
