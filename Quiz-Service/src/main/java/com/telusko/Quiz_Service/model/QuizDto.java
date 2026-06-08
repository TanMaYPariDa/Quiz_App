package com.telusko.Quiz_Service.model;

import lombok.Data;

@Data
public class QuizDto {

    private String category;
    private int numOfQ;
    private String title;

}
