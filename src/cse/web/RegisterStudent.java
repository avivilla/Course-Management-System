package cse.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register complete Student registration
 */
@WebServlet("/RegisterStudent")
public class RegisterStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBHelper db;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterStudent() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		RequestDispatcher view=request.getRequestDispatcher("register.jsp");
//		view.forward(request,response);
		// get the paramaters
		// String username=request.getParameter("username");
		if (!logs.Uid.equals("")) {
			if (logs.Utp.equals("admin")) {
				RequestDispatcher view = request.getRequestDispatcher("HomeAdmin");
				view.forward(request, response);
			} else if (logs.Utp.equals("teacher")) {
				RequestDispatcher view = request.getRequestDispatcher("HomeTeacher");
				view.forward(request, response);
			} else if (logs.Utp.equals("student")) {
				RequestDispatcher view = request.getRequestDispatcher("ShowCourseStudent");
				view.forward(request, response);
			} else {
				RequestDispatcher view = request.getRequestDispatcher("Home01.jsp");
				view.forward(request, response);
			}

		} else {
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String reg = request.getParameter("reg");
			String email = request.getParameter("email");
			//create hash
			HashClass hashClass = new HashClass();
			String HashPass = null;
			try {
				HashPass = HashClass.generateStorngPasswordHash(password);
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Add in Database
			db = new DBHelper();
			db.AddStudent(HashPass, name, reg, email);
			RequestDispatcher view = request.getRequestDispatcher("login.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
