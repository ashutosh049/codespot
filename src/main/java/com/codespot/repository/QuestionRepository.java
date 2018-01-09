package com.codespot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

//import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.codespot.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>, PagingAndSortingRepository<Question, Long>{
	
}
