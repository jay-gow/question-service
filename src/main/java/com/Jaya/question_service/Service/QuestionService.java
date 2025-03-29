package com.Jaya.question_service.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Jaya.question_service.DAO.QuestionDao;
import com.Jaya.question_service.Model.QuestionWrapper;
import com.Jaya.question_service.Model.Response;
import com.Jaya.question_service.Model.quiz_questions;


@Service
public class QuestionService {
   
	@Autowired
	 QuestionDao  questionDao;
	public ResponseEntity<List<quiz_questions>> getAllQuestions()
	{
		try {
	return new ResponseEntity( questionDao.findAll(),HttpStatus.OK);}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new ResponseEntity( new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}
	public ResponseEntity<List<quiz_questions>> getQuestionByLanguage(String language) {
		try {
			return new ResponseEntity( questionDao.findByLanguage(language),HttpStatus.OK);}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				return new ResponseEntity( new ArrayList<>(),HttpStatus.BAD_REQUEST);
		
	}
	public ResponseEntity<String> addQuestion(quiz_questions ques) {
		 questionDao.save(ques);
		 return new ResponseEntity("success",HttpStatus.CREATED);
		
	}
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String language, Integer numQuestions) {
		List<Integer> questions=questionDao.findRandomQuestionsByLanguage(language,numQuestions);
	
		return new ResponseEntity<>(questions,HttpStatus.CREATED);
	}
	public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questionIds) {
		List<QuestionWrapper> wrappers=new ArrayList<>();
		List<quiz_questions> questions=new ArrayList<>();
		
		for(Integer id:questionIds) {
			questions.add(questionDao.findById(id).get());
		}
		for(quiz_questions q:questions) {
			QuestionWrapper wrapper=new QuestionWrapper();
			wrapper.setQuestion_id(q.getQuestion_id());
			wrapper.setQuestion_title(q.getQuestion_title());
			wrapper.setOption1(q.getOption1());
			wrapper.setOption2(q.getOption2());
			wrapper.setOption3(q.getOption3());
			wrapper.setOption4(q.getOption4());
			wrappers.add(wrapper);
		}
		return new ResponseEntity<>(wrappers,HttpStatus.OK);
	}
	public ResponseEntity<Integer> getScore(List<Response> responses) {
		int right=0;
		
		for(Response response:responses) {
			quiz_questions questions=questionDao.findById(response.getId()).get();
			if(response.getResponse().equals(questions.getRight_answer())){
				right++;
			}
		
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}
      
}
