package cse.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Complete adding new course by adding it in DB and redirect to admin home
 */
@WebServlet("/CompleteCourseRegistration")
public class CompleteCourseRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompleteCourseRegistration() {
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
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		// TODO Auto-generated method stub
		//System.out.println("cole");
		if (!logs.Utp.equals("admin")) {
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
			String title = request.getParameter("title");
			String code = request.getParameter("code");
			String teachername = request.getParameter("teachername");
			String teacherid = request.getParameter("teacherid");
			//add to DB
			DBHelper db = new DBHelper();
			db.addCourse(code, title, teacherid, teachername);
			RequestDispatcher view = request.getRequestDispatcher("HomeAdmin.jsp");
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
