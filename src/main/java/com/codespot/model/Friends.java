<<<<<<< HEAD
package com.codespot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@NamedQuery(name="Friends.findAll", query="SELECT f FROM Friends f")
public class Friends implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FRIEND_FRIENDID_GENERATOR", sequenceName="ORDER_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="FRIEND_FRIENDID_GENERATOR")
	@Column(name="relationship_id")
	private Long relationshipId;
	
	@ManyToOne
	@JoinColumn(name="relation_from")
	private User relationFrom;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="relation_with")
	private User relationWith;
	
	
	@Column(name="status")
	private int status;
	
	public Friends() {}

	public Friends(Long relationshipId, User relationFrom, User relationWith, int status) {
		super();
		this.relationshipId = relationshipId;
		this.relationFrom = relationFrom;
		this.relationWith = relationWith;
		this.status = status;
	}
	
	public Friends(User relationFrom, User relationWith, int status) {
		super();
		this.relationFrom = relationFrom;
		this.relationWith = relationWith;
		this.status = status;
	}

	public Long getRelationshipId() {
		return relationshipId;
	}

	public void setRelationshipId(Long relationshipId) {
		this.relationshipId = relationshipId;
	}

	public User getRelationFrom() {
		return relationFrom;
	}

	public void setRelationFrom(User relationFrom) {
		this.relationFrom = relationFrom;
	}

	public User getRelationWith() {
		return relationWith;
	}

	public void setRelationWith(User relationWith) {
		this.relationWith = relationWith;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
=======
package com.codespot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@NamedQuery(name="Friends.findAll", query="SELECT f FROM Friends f")
public class Friends implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="FRIEND_FRIENDID_GENERATOR", sequenceName="ORDER_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="FRIEND_FRIENDID_GENERATOR")
	@Column(name="relationship_id")
	private Long relationshipId;
	
	@ManyToOne
	@JoinColumn(name="relation_from")
	private User relationFrom;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="relation_with")
	private User relationWith;
	
	
	@Column(name="status")
	private int status;
	
	public Friends() {}

	public Friends(Long relationshipId, User relationFrom, User relationWith, int status) {
		super();
		this.relationshipId = relationshipId;
		this.relationFrom = relationFrom;
		this.relationWith = relationWith;
		this.status = status;
	}
	
	public Friends(User relationFrom, User relationWith, int status) {
		super();
		this.relationFrom = relationFrom;
		this.relationWith = relationWith;
		this.status = status;
	}

	public Long getRelationshipId() {
		return relationshipId;
	}

	public void setRelationshipId(Long relationshipId) {
		this.relationshipId = relationshipId;
	}

	public User getRelationFrom() {
		return relationFrom;
	}

	public void setRelationFrom(User relationFrom) {
		this.relationFrom = relationFrom;
	}

	public User getRelationWith() {
		return relationWith;
	}

	public void setRelationWith(User relationWith) {
		this.relationWith = relationWith;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
>>>>>>> post-chat
}