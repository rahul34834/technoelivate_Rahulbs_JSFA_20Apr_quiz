package com.technoelivate.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.technoelivate.DatabaseConnectionFactory;
import com.technoelivate.Exam;

/**
 * Servlet implementation class UserPanelController
 */
@WebServlet("/userPanel")
public class UserPanelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserPanelController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
			// Get the username information from the Http Session
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("user");
			session.setAttribute("user", username);

			// Retrieve User Test Results from the database and return it as a LinkedList
			LinkedList<Integer> tests = DatabaseConnectionFactory.retrieveUserData(username);

			ListIterator<Integer> listIterator = tests.listIterator();
			Integer[] array = new Integer[50]; 
			int i = 0;
			
			// Iterate through the list and get the data 
			while (listIterator.hasNext()) {
				array[i] = listIterator.next();
				i++;
			}
			
			float multiplier = 100/(Exam.numberOfQuestions);
			
			// set the data as attributes of the request
			request.setAttribute("java", multiplier*array[0]);
			request.setAttribute("javascript", multiplier*array[1]);
			request.setAttribute("sequel", multiplier*array[2]);
			request.setAttribute("python", multiplier*array[3]);
			request.setAttribute("css", multiplier*array[4]);
			request.setAttribute("php", multiplier*array[5]);
			request.setAttribute("c", multiplier*array[6]);
			request.setAttribute("ruby", multiplier*array[7]);

			// Send this information to the userPanel.jsp
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsps/userPanel.jsp");
			rd.forward(request, response);
		}
	}


