package com.technoelivate.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.technoelivate.DatabaseConnectionFactory;
import com.technoelivate.SecurityMechanism;

/**
 * Servlet implementation class RegistrationController
 */
@WebServlet("/checkRegister")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationController() {
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

		// Get the form fields filled by the user
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		//Create the DB if non-existent
		DatabaseConnectionFactory.createDB();
		
		// Create the table in the database if non-existent
		DatabaseConnectionFactory.createTable();
		
		Connection con = DatabaseConnectionFactory.createConnection();
		boolean error = false;

		// Check if the username exists in the database
		boolean exists = DatabaseConnectionFactory.checkUsername(username);

		try {
			Statement st = con.createStatement();
			SecurityMechanism sm = new SecurityMechanism();
			String sql = "INSERT INTO users values ('" + username + "','" + sm.getEncrypted(password) + "','" + email + "'"
					+ ",0,0,0,0,0,0,0,0)";
			st.execute(sql);
			
			error = false;
		} catch (SQLException sqe) {
			System.out.println("Duplicate Key Error : While Inserting record into the database");
			sqe.printStackTrace();
			error = true;
			
		}
		try {
			con.close();
		} catch (SQLException se) {
			System.out.println("Error : While Closing Connection");
			error = true;
		}
		request.setAttribute("newUser", username);

		if (!error && !exists) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/regSuccess.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsps/regFailure.jsp");
			dispatcher.forward(request, response);
		}
	
	}

}
