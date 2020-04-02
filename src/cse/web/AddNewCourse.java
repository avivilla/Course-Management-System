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
 * Servlet implementation class AddNewCourse 
 * This class query all the teacher from the database and redirect them as request attribute to the AddNewCourse.jsp
 */
@WebServlet("/AddNewCourse")
public class AddNewCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddNewCourse() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//check if signed in as admin
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
			//database query
			DBHelper db = new DBHelper();
			ResultSet rs = db.getTeacher();
			
			ArrayList al = null;
			ArrayList courselist = new ArrayList();
			//add in arraylist
			try {
				while (rs.next()) {
					al = new ArrayList();
					try {
						al.add(rs.getString("id"));
						al.add(rs.getString("name"));
						al.add(rs.getString("description"));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					
					courselist.add(al);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//forward to jsp
			request.setAttribute("courselist", courselist);
			RequestDispatcher view = request.getRequestDispatcher("AddNewCourse.jsp");
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
