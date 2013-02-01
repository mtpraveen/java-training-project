package com.travel.web.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TestX
 */
@WebServlet("/TestX")
public class TestX extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestX() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Date date = (Date) session.getAttribute("date");
		if (date == null)
		{
			date = new Date();
			session.setAttribute("date", date);
		}	
		response.getWriter().println(date);
		response.getWriter().println("<br/>");

		Date date2 = (Date) request.getAttribute("date2");
		if (date2 == null)
		{
			date2 = new Date();
			request.setAttribute("date2", date2);
		}	
		response.getWriter().println(date2);
	}
}
