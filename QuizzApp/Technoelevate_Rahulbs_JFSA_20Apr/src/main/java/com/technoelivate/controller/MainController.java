package com.technoelivate.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainController
 */
@WebServlet(urlPatterns = { "/login", "/register", "/takeExam", "/logout" })
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String applicationContextPath = request.getContextPath();

		if (request.getRequestURI().equals(applicationContextPath + "/")) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/WEB-INF/jsps/home.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equals(
				applicationContextPath + "/login")) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/WEB-INF/jsps/login.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equals(
				applicationContextPath + "/register")) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/WEB-INF/jsps/register.jsp");
			dispatcher.forward(request, response);
		} else if (request.getRequestURI().equals(
				applicationContextPath + "/takeExam")) {
			request.getSession().setAttribute("currentExam", null);

			String exam = request.getParameter("test");
			request.getSession().setAttribute("exam", exam);

			System.out.println(request.getSession().getAttribute("user"));
			if (request.getSession().getAttribute("user") == null) {
				request.getRequestDispatcher("/login").forward(request,
						response);
				
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/WEB-INF/jsps/quizDetails.jsp");
				dispatcher.forward(request, response);
			}
		} else if (request.getRequestURI().equals(
				applicationContextPath + "/logout")) {
			request.getSession().invalidate();
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/WEB-INF/jsps/home.jsp");
			dispatcher.forward(request, response);
		}
		else if (request.getRequestURI().equals(
				applicationContextPath + "/userPanel")) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/WEB-INF/jsps/userPanel.jsp");
			dispatcher.forward(request, response);
		} 

	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
