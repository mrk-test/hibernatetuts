package com.mnazareno.hibernatetuts.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mnazareno.hibernatetuts.domain.Event;
import com.mnazareno.hibernatetuts.util.HibernateUtils;

public class EventManagerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			HibernateUtils.getSessionFactory().getCurrentSession()
					.beginTransaction();

			PrintWriter out = response.getWriter();
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

			out.println("<html><head><title>Event Manager</title></head><body>");

			if ("store".equals(request.getParameter("action"))) {
				String title = request.getParameter("eventTitle");
				Date d = df.parse(request.getParameter("eventDate"));
				store(title, d);
			}

			printEventForm(out);
			listEvents(out);

			out.println("</body></html>");
			out.flush();
			out.close();

			HibernateUtils.getSessionFactory().getCurrentSession()
					.getTransaction().commit();
		} catch (Exception ex) {
			HibernateUtils.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();

			if (ServletException.class.isInstance(ex)) {
				throw (ServletException) ex;
			} else {
				throw new ServletException(ex);
			}
		}
	}

	private void printEventForm(PrintWriter out) {
		out.println("<h2>Add new event:</h2>");
		out.println("<form>");
		out.println("Title: <input name='eventTitle' length='50'/><br/>");
		out.println("Date (e.g. 24.12.2009): <input name='eventDate' length='10'/><br/>");
		out.println("<input type='submit' name='action' value='store'/>");
		out.println("</form>");
	}

	@SuppressWarnings("unchecked")
	private void listEvents(PrintWriter out) {
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

		List<Event> events = HibernateUtils.getSessionFactory()
				.getCurrentSession().createQuery("from Event").list();
		if (events.isEmpty()) {
			out.println("<h2>There are currently no events.</h2>");
		} else {
			out.println("<h2>Events in database:</h2>");
			out.println("<table border='1'>");
			out.println("<tr>");
			out.println("<th>Event title</th>");
			out.println("<th>Event date</th>");
			out.println("</tr>");
			for (Event event : events) {
				out.println("<tr>");
				out.println("<td>" + event.getTitle() + "</td>");
				out.println("<td>" + df.format(event.getDate()) + "</td>");
				out.println("</tr>");
			}
			out.println("</table>");
		}
	}

	private void store(String title, Date d) {
		HibernateUtils.getSessionFactory().getCurrentSession()
				.save(new Event(title, d));
	}
}
