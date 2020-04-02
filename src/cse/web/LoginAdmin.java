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
 * Servlet implementation class LoginAdmin Admin login page
 */
@WebServlet("/LoginAdmin")
public class LoginAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginAdmin() {
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
		// check if logged in
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
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			// check null
			if (username == null || password == null) {
				RequestDispatcher view = request.getRequestDispatcher("LoginAdmin.jsp");
				view.forward(request, response);
			}
			// Encrypting password
			else {
				HashClass hashClass = new HashClass();
				// checkt DB
				DBHelper db = new DBHelper();
				Boolean matched = null;
				String res = db.LoginQueryAdmin(username);
				if (res == null) {
					RequestDispatcher view = request.getRequestDispatcher("LoginAdmin.jsp");
					view.forward(request, response);
				} else {
//					validate password
					try {
						matched = hashClass.validatePassword(password, res);
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvalidKeySpecException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// redirect
					if (matched) {
						request.setAttribute("username", username);
						logs.Utp = "admin";
						logs.Uid = username;
						RequestDispatcher view = request.getRequestDispatcher("HomeAdmin");
						view.forward(request, response);
					} else {

						RequestDispatcher view = request.getRequestDispatcher("LoginAdmin.jsp");
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
