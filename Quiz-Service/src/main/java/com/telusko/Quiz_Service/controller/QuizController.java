package com.telusko.Quiz_Service.controller;

import com.telusko.Quiz_Service.model.QuestionWrapper;
import com.telusko.Quiz_Service.model.QuizDto;
import com.telusko.Quiz_Service.model.UserResponse;
import com.telusko.Quiz_Service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz
            (@RequestBody QuizDto quizDto)//instead of getting the parameters one by one we can get them all in one single object
    {
        return quizService.createQuiz(quizDto.getCategory(),quizDto.getNumOfQ(),quizDto.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> getResult(@PathVariable Integer id,@RequestBody List<UserResponse> responses){
        return quizService.getResult(id,responses);
    }

}
