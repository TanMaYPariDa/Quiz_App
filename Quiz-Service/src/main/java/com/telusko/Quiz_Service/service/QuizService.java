package com.telusko.Quiz_Service.service;

import com.telusko.Quiz_Service.dao.QuizDao;
import com.telusko.Quiz_Service.feign.QuizFeignClient;
import com.telusko.Quiz_Service.model.QuestionWrapper;
import com.telusko.Quiz_Service.model.Quiz;
import com.telusko.Quiz_Service.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizFeignClient quizFeignClient;

    public ResponseEntity<String> createQuiz(String category, int numofQ, String title) {

        List<Integer> questionIds = quizFeignClient.getQuestionsForQuiz(category,numofQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionIds);

        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Integer> questionIds = quiz.get().getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> userQuestions = quizFeignClient.getQuestionsFromId(questionIds);

        return userQuestions;
    }

    public ResponseEntity<Integer> getResult(Integer id, List<UserResponse> responses) {
        ResponseEntity<Integer> res = quizFeignClient.getResult(responses);

        return res;
    }
}