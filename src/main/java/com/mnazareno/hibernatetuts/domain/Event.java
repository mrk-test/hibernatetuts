package com.mnazareno.hibernatetuts.domain;

import java.util.Date;
import java.util.Set;

public class Event {
	
	private Long id;
	
	private String title;
	
	private Date date;
	
	private Set<Person> participants;
	
	public Event() {
	}

	public Event(String title, Date d) {
		this.title = title;
		this.date = d;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<Person> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Person> participants) {
		this.participants = participants;
	}
}
