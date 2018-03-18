package com.codespot.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;
import java.sql.Timestamp;

/**
 * The persistent class for the question database table.
 * 
 */
@Entity
@NamedQuery(name="Question.findAll", query="SELECT q FROM Question q")
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="QUESTION_QUESTIONID_GENERATOR", sequenceName="ORDER_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="QUESTION_QUESTIONID_GENERATOR")
	@Column(name="question_id", updatable=false)
	private long questionId;

	@Column(name="question_title")
	private String questionTitle;

	@Column(name="question_file_path")
	private String questionFilePath;
	
	@Column(name="question_description")
	private String questionDescription;
	
	@Column(name="question_short_description")
	private String questionShortDescription;
	
	@Column(name="question_tags")
	private String questionTags;
	
	@Column(name="create_timestamp")
	private Timestamp  createTimestamp;

	@Column(name="last_modified_timestamp")
	private Timestamp  lastModifiedTimestamp;

	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "question", cascade = CascadeType.ALL)
	private Set<Comment> comments;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public Question() {
	}

	public Long getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public String getQuestionFilePath() {
		return this.questionFilePath;
	}

	public void setQuestionFilePath(String questionFilePath) {
		this.questionFilePath = questionFilePath;
	}

	public String getQuestionTitle() {
		return this.questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setQuestion(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setQuestion(null);

		return comment;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getQuestionTags() {
		return questionTags;
	}

	public void setQuestionTags(String questionTags) {
		this.questionTags = questionTags;
	}

	public String getQuestionDescription() {
		return questionDescription;
	}

	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}

	public String getQuestionShortDescription() {
		return questionShortDescription;
	}

	public void setQuestionShortDescription(String questionShortDescription) {
		this.questionShortDescription = questionShortDescription;
	}

	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public Timestamp getLastModifiedTimestamp() {
		return lastModifiedTimestamp;
	}

	public void setLastModifiedTimestamp(Timestamp lastModifiedTimestamp) {
		this.lastModifiedTimestamp = lastModifiedTimestamp;
	}


}