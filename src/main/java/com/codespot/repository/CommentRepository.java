package com.codespot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codespot.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}
