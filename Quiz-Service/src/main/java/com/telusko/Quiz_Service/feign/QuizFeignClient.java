package com.telusko.Quiz_Service.feign;

import com.telusko.Quiz_Service.model.QuestionWrapper;
import com.telusko.Quiz_Service.model.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizFeignClient {
    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
            (@RequestParam String category, @RequestParam Integer numOfQ);

    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);

    @GetMapping("question/getScore")
    public ResponseEntity<Integer> getResult(@RequestBody List<UserResponse> responses);
}