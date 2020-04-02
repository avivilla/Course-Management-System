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
 * Servlet implementation class LoginTeacher
 * Check teacher login . 
 */
@WebServlet("/LoginTeacher")
public class LoginTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginTeacher() {
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
		//check login
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
			}
			else {
				RequestDispatcher view = request.getRequestDispatcher("Home01.jsp");
				view.forward(request, response);
			}

		} else {
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			if (id == null || password == null) {
				System.out.println("1");
				RequestDispatcher view = request.getRequestDispatcher("LoginTeacher.jsp");
				view.forward(request, response);
			}
			// Encrypting password
			else {
				//get pass from DB
				HashClass hashClass = new HashClass();
				DBHelper db = new DBHelper();
				Boolean matched = null;
				String res = db.LoginQueryTeacher(id);
				if (res == null) {
					System.out.println("2");
					RequestDispatcher view = request.getRequestDispatcher("LoginTeacher.jsp");
					view.forward(request, response);
				} else {
					//validate
					try {

						matched = hashClass.validatePassword(password, res);
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvalidKeySpecException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//redirect
					if (matched) {
						request.setAttribute("id", id);
						logs.Utp = "teacher";
						logs.Uid = id;
						RequestDispatcher view = request.getRequestDispatcher("HomeTeacher");
						view.forward(request, response);
					} else {
						
						RequestDispatcher view = request.getRequestDispatcher("LoginTeacher.jsp");
						view.forward(request, response);
					}
				}
			}
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
