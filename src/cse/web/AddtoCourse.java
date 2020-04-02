package cse.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * add the registration info of a course
 */
@WebServlet("/AddtoCourse")
public class AddtoCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddtoCourse() {
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// get parameters
		// TODO Auto-generated method stub
		if (!logs.Utp.equals("student")) {
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
			String code = request.getParameter("code");
			String title = request.getParameter("title");
			String teacherid = request.getParameter("teacherid");
			String teachername = request.getParameter("teachername");
			String reg = request.getParameter("reg");
			// add to DB
			DBHelper db = new DBHelper();
			db.RegisterForCourse(code, title, teacherid, teachername, reg);
			System.out.println("done");
			request.setAttribute("reg", reg);
			// redirect to home
			RequestDispatcher view = request.getRequestDispatcher("ShowCourseStudent");
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
