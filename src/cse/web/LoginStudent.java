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
 * Servlet implementation class Login
 * Checks login credintials and redirect.
 */
@WebServlet("/LoginStudent")
public class LoginStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginStudent() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	private boolean OK(String str) {
		return str != null && !str.isEmpty();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
//		RequestDispatcher view=request.getRequestDispatcher("Home01.jsp");
//		view.forward(request,response);
		// init Driver
 //if already logged in send to home
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
			String reg = request.getParameter("reg");
			String password = request.getParameter("password");
			//check null
			if (!OK(reg) || !OK(password)) {
				RequestDispatcher view = request.getRequestDispatcher("LoginStudent.jsp");
				view.forward(request, response);
			}
			// Encrypting password
			else {
				HashClass hashClass = new HashClass();

				DBHelper db = new DBHelper();
				Boolean matched = null;
				String res = db.LoginQuery(reg);
				System.out.println("cole " + res);
				if (res == null) {
					RequestDispatcher view = request.getRequestDispatcher("LoginStudent.jsp");
					view.forward(request, response);
				} else {
					try {

						matched = hashClass.validatePassword(password, res);
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvalidKeySpecException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//redirect if match
					if (matched) {
						request.setAttribute("reg", reg);
						logs.Utp = "student";
						logs.Uid = reg;
						RequestDispatcher view = request.getRequestDispatcher("ShowCourseStudent");
						view.forward(request, response);
					} else {

						RequestDispatcher view = request.getRequestDispatcher("LoginStudent.jsp");
						view.forward(request, response);
					}
				}
			}
		}
	}
	//

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
