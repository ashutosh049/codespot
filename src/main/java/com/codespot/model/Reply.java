package com.codespot.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;


/**
 * The persistent class for the reply database table.
 * 
 */
@Entity
@NamedQueries(value = { 
		@NamedQuery(name="Reply.findAll", query="SELECT r FROM Reply r")
})
//@NamedQuery(name="Reply.findAll", query="SELECT r FROM Reply r")
public class Reply implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REPLY_REPLYID_GENERATOR", sequenceName="ORDER_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="REPLY_REPLYID_GENERATOR")
	@Column(name="reply_id", updatable=false)
	private long replyId;

	@Column(name="reply_description")
	private String replyDescription;
	
	@Column(name="create_timestamp")
	private Timestamp  createTimestamp;

	@Column(name="last_modified_timestamp")
	private Timestamp  lastModifiedTimestamp;

	@ManyToOne
	@JoinColumn(name="comment_id")
	private Comment comment;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public Reply() {
	}

	public Long getReplyId() {
		return this.replyId;
	}

	public void setReplyId(long replyId) {
		this.replyId = replyId;
	}

	public String getReplyDescription() {
		return this.replyDescription;
	}

	public void setReplyDescription(String replyDescription) {
		this.replyDescription = replyDescription;
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

	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
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
	    };

}