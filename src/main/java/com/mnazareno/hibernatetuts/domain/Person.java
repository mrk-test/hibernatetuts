package com.mnazareno.hibernatetuts.domain;

import java.util.HashSet;
import java.util.Set;

public class Person {
	
	private Long id;
	
	private int age;
	
	private String firstname;
	
	private String lastname;
	
	private Set<Event> events;
	
	private Set<String> emailAddresses = new HashSet<String>();
	
	public Person() {
	}
	
	public Person(String firstname, String lastname, int age) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return String.format("Person[id=%s, firstname=%s, lastname=%s, age=%d]", id, firstname, lastname, age);
	}
	
	public void addToEvent(Event event) {
		events.add(event);
		event.getParticipants().add(this);
	}
	
	public void removeFromEvent(Event e) {
		events.remove(e);
		e.getParticipants().remove(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	protected Set<Event> getEvents() {
		return events;
	}

	protected void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Set<String> getEmailAddresses() {
		return emailAddresses;
	}

	public void setEmailAddresses(Set<String> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}
	
	public static void main(String[] args) {
		Person p = new Person("John", "Doe", 22);
		System.out.println(p);
	}
}
