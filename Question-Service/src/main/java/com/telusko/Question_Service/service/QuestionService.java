package com.telusko.Question_Service.service;

import com.telusko.Question_Service.dao.QuestionDao;
import com.telusko.Question_Service.model.Question;
import com.telusko.Question_Service.model.QuestionWrapper;
import com.telusko.Question_Service.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestion(){
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getByCategory(String category){
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question){
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Successfully Added", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error Adding...", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteQuestion(int id) {
        try {
            questionDao.deleteById(id);
            return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error Deleting...", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> getQuestions(String category, Integer numOfQ) {

        List<Integer> questionIds = questionDao.findRandomQuestionsByCategory(category,numOfQ);

        return new ResponseEntity<>(questionIds,HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Integer id : questionIds){
            questions.add(questionDao.findById(id).get());
        }

        for (Question q : questions){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(q.getId());
            wrapper.setQuestion_title(q.getQuestion_title());
            wrapper.setOption1(q.getOption1());
            wrapper.setOption2(q.getOption2());
            wrapper.setOption3(q.getOption3());
            wrapper.setOption4(q.getOption4());
            wrappers.add(wrapper);
        }

        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getResult(List<UserResponse> responses) {
          int res = 0;

          for (UserResponse u : responses){
              Optional<Question> q = questionDao.findById(u.getId());
              if(u.getResponse().equals(q.get().getRight_answer()))
                  res++;
          }

          return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
