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
 * Servlet implementation class ShowCourseStudent
 *Show courses taken by a student 
 */
@WebServlet("/ShowCourseStudent")
public class ShowCourseStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowCourseStudent() {
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
			String reg = logs.Uid;
			//get data from DB
			DBHelper db = new DBHelper();
			ResultSet rs = db.QueryCourseByReg(reg);
			ArrayList al = null;
			ArrayList courselist = new ArrayList();
			try {

				while (rs.next()) {
					al = new ArrayList();
					try {
						al.add(rs.getString("code"));
						al.add(rs.getString("title"));
						al.add(rs.getString("teachername"));

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
			//redirect to jsp
			request.setAttribute("courselist", courselist);
			RequestDispatcher view = request.getRequestDispatcher("ShowCourseStudent.jsp");
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
