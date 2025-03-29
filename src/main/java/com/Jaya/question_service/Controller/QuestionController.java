package com.Jaya.question_service.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Jaya.question_service.Model.QuestionWrapper;
import com.Jaya.question_service.Model.Response;
import com.Jaya.question_service.Model.quiz_questions;
import com.Jaya.question_service.Service.QuestionService;


@RestController
@RequestMapping("question")
public class QuestionController
{
	@Autowired
	QuestionService questionService;
	@Autowired
    private Environment environment;  
	@GetMapping("allquestion")
   public ResponseEntity<List<quiz_questions>> getAllQuestion() {
	   return  questionService.getAllQuestions();
   }
   @GetMapping("category/{language}")
   public   ResponseEntity< List<quiz_questions>> getQuestionsByLanguage(@PathVariable String language){
	   return questionService.getQuestionByLanguage(language);
   }
   @PostMapping("add")
   public  ResponseEntity<String> addQuestion(@RequestBody quiz_questions ques)
   {
	 return  questionService.addQuestion(ques);
   }
   @GetMapping("generate")
   public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String language,@RequestParam Integer numQuestions ) {
	   return questionService.getQuestionsForQuiz(language,numQuestions);
   }
   
   @PostMapping("getQuestions")
   public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
	   System.out.println( environment.getProperty("local.server.port"));
	 return questionService.getQuestionFromId(questionIds)  ;
   }
   
   @PostMapping("getScore")
   public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
	 
return questionService.getScore(responses);
	} 
	
   
   
}
//generate
//getQuestion(Question id)
//getScore  
