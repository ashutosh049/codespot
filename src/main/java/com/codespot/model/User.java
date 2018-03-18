package com.codespot.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USER_USERID_GENERATOR", sequenceName="ORDER_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.AUTO, generator="USER_USERID_GENERATOR")
	@Column(name="user_id", updatable=false)
	private Long userId;

	private String activationTokem;

	@Column(name="user_active")
	private boolean userActive;

	@Column(name="user_email")
	private String userEmail;

	@Column(name="user_name")
	private String userName;

	@Column(name="user_password")
	private String userPassword;
	
	@Transient
	@NotBlank(message = "confirmPassword.")
	private String confirmPassword;

	@JsonIgnore
	@OneToMany(mappedBy="user")
	private Set<Comment> comments;

	@JsonIgnore
	@OneToMany(mappedBy="user")
	private Set<Question> questions;

	@JsonIgnore
	@OneToMany(mappedBy="user")
	private Set<Reply> replies;

	@JsonIgnore
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(
		name="user_role"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="role_id")
			}
		)
	private Set<Role> roles;
	
/*	@JsonIgnore
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(
		name="Friends"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="frnd_id")
			}
		)
	private Set<User> userfriends;
*/
	@JsonIgnore
	@OneToMany(mappedBy="relationFrom",cascade={CascadeType.ALL})
	private Set<Friends> userfriends;
	
	/*@JsonIgnore
	@ManyToOne(mappedBy="relation_with",cascade={CascadeType.ALL})
	private Set<Friends> relationWith;*/

	public User() {
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getActivationTokem() {
		return this.activationTokem;
	}

	public void setActivationTokem(String activationTokem) {
		this.activationTokem = activationTokem;
	}

	public boolean getUserActive() {
		return this.userActive;
	}

	public void setUserActive(boolean userActive) {
		this.userActive = userActive;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setUser(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setUser(null);

		return comment;
	}

	public Set<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public Question addQuestion(Question question) {
		getQuestions().add(question);
		question.setUser(this);

		return question;
	}

	public Question removeQuestion(Question question) {
		getQuestions().remove(question);
		question.setUser(null);

		return question;
	}

	public Set<Reply> getReplies() {
		return this.replies;
	}

	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}

	public Reply addReply(Reply reply) {
		getReplies().add(reply);
		reply.setUser(this);

		return reply;
	}

	public Reply removeReply(Reply reply) {
		getReplies().remove(reply);
		reply.setUser(null);

		return reply;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Friends> getUserfriends() {
		return userfriends;
	}

	public void setUserfriends(Set<Friends> userfriends) {
		this.userfriends = userfriends;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final User other = (User) obj;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }

}