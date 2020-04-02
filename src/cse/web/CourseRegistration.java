package cse.web;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CourseRegistration
 * Register a course taken by student
 */
@WebServlet("/CourseRegistration")
public class CourseRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CourseRegistration() {
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
			//DB quey
			String reg = logs.Uid;
			DBHelper db = new DBHelper();
			//Send them as arraylist
			ResultSet rs = db.CoursesToRegister(reg);
			ArrayList al = null;
			ArrayList courselist = new ArrayList();
			try {

				while (rs.next()) {
					al = new ArrayList();

					System.out.println(rs.getString("code"));
					System.out.println(rs.getString("title"));
					System.out.println(rs.getString("teachername"));
					System.out.println(rs.getString(4));
					try {
						al.add(rs.getString("code"));
						al.add(rs.getString("title"));
						al.add(rs.getString("teacherid"));
						al.add(rs.getString("teachername"));
						al.add(reg);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// System.out.println("al :: " + al);
					courselist.add(al);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//forward
			request.setAttribute("courselist", courselist);
			RequestDispatcher view = request.getRequestDispatcher("CourseRegistration.jsp");
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
