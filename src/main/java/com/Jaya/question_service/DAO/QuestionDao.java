
package com.Jaya.question_service.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Jaya.question_service.Model.quiz_questions;

@Repository
public interface QuestionDao extends JpaRepository<quiz_questions, Integer>
{

	         List<quiz_questions>  findByLanguage(String language);
	         
	         @Query(value="SELECT q.question_id FROM quiz_questions q WHERE q.language = :language ORDER BY RANDOM() LIMIT :num", nativeQuery = true)
	         List<Integer> findRandomQuestionsByLanguage(String language, int num);

}
