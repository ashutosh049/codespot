package com.codespot.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the comment database table.
 * 
 */
@Entity
@NamedQueries(value = { 
		@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
})
//@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="COMMENT_COMMENTID_GENERATOR", sequenceName="ORDER_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="COMMENT_COMMENTID_GENERATOR")
	@Column(name="comment_id", updatable=false)
	private long commentId;

	@Column(name="comment_description")
	private String commentDescription;

	@Column(name="create_timestamp")
	private Timestamp  createTimestamp;

	@Column(name="last_modified_timestamp")
	private Timestamp  lastModifiedTimestamp;
	
	@ManyToOne
	@JoinColumn(name="question_id")
	private Question question;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;

	@JsonIgnore
	@OneToMany(mappedBy="comment")
	private Set<Reply> replies;

	public Comment() {
	}

	public Long getCommentId() {
		return this.commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public String getCommentDescription() {
		return this.commentDescription;
	}

	public void setCommentDescription(String commentDescription) {
		this.commentDescription = commentDescription;
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

	public Question getQuestion() {
		return this.question;
	}
	
	public void setQuestion(Question question) {
		this.question = question;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Reply> getReplies() {
		return this.replies;
	}

	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}

	public Reply addReply(Reply reply) {
		getReplies().add(reply);
		reply.setComment(this);

		return reply;
	}

	public Reply removeReply(Reply reply) {
		getReplies().remove(reply);
		reply.setComment(null);

		return reply;
	}
	
	 public enum OPERATION {
	        INSERT, UPDATE, DELETE;
	        private String value;

	        OPERATION() {
	            value = toString();
	        }

	        public String getValue() {
	            return value;
	        }

	        public static OPERATION parse(final String value) {
	            OPERATION operation = null;
	            for (final OPERATION op : OPERATION.values()) {
	                if (op.getValue().equals(value)) {
	                    operation = op;
	                    break;
	                }
	            }
	            return operation;
	        }
	    }

}