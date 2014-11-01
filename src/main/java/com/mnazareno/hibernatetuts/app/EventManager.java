package com.mnazareno.hibernatetuts.app;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.mnazareno.hibernatetuts.domain.Event;
import com.mnazareno.hibernatetuts.domain.Person;
import com.mnazareno.hibernatetuts.util.HibernateUtils;

public class EventManager {

	public static void main(String[] args) {
		EventManager mgr = new EventManager();

		if (args.length > 0 && args[0].equals("list")) {
			for (Event e : mgr.listEvents()) {
				System.out.println("Event: " + e.getTitle() + " Time: "
						+ e.getDate());
			}
		} else if (args[0].equals("store")) {
			mgr.createEvent("The Event", new Date());
		} else if (args[0].equals("addpersontoevent")) {
			Long eventId = mgr.createEvent("Some Event", new Date());
			Long personId = mgr.createPerson(new Person("John", "Doe", 20));
			mgr.addPersonToEvent(eventId, personId);
			System.out.println("Added person " + personId + " to event " + eventId);
		} else if (args[0].equals("addemailtoperson")) {
			Long personId = mgr.createPerson(new Person("Jane", "Dow", 23));
			Person p  = mgr.addEmailToPerson(personId, "jane.dow@mail.com");
			System.out.println(p);
		}
	}

	public Long createEvent(String title, Date d) {
		Session session = HibernateUtils.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();

		Event e = new Event();
		e.setTitle(title);
		e.setDate(d);
		Long id = (Long) session.save(e);

		session.getTransaction().commit();
		
		return id;
	}
	
	public Long createPerson(Person p) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Long id = (Long) session.save(p);
		
		session.getTransaction().commit();
		
		return id;
	}

	@SuppressWarnings("unchecked")
	public List<Event> listEvents() {
		Session session = HibernateUtils.getSessionFactory()
				.getCurrentSession();
		session.beginTransaction();

		List<Event> result = session.createQuery("from Event").list();

		session.getTransaction().commit();

		return result;
	}
	
	public Person addPersonToEvent(Long eventId, Long personId) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Event event = (Event) session.load(Event.class, eventId);
		Person person = (Person) session.load(Person.class, personId);
		person.addToEvent(event);
		
		session.getTransaction().commit();
		
		return person;
	}
	
	public Person addEmailToPerson(Long personId, String email) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Person person = (Person) session.load(Person.class, personId);
		person.getEmailAddresses().add(email);
		
		session.getTransaction().commit();
		
		return person;
	}
}
