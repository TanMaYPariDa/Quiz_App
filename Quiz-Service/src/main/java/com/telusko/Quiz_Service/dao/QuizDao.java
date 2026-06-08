package com.telusko.Quiz_Service.dao;

import com.telusko.Quiz_Service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz,Integer> {
}
