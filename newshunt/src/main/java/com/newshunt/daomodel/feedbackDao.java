package com.newshunt.daomodel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="feedback")
public class feedbackDao 
{
	@Id
	@GeneratedValue
	int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getQuerry() {
		return querry;
	}
	public void setQuerry(String querry) {
		this.querry = querry;
	}
	String email;
	String subject;
	String querry;

}
