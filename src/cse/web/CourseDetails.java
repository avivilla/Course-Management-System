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
 * Servlet implementation class CourseDetails
 * Show Course Details taken by 
 */
@WebServlet("/CourseDetails")
public class CourseDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CourseDetails() {
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
		if (!logs.Utp.equals("teacher")) {
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
//		System.out.println(reg);
			DBHelper db = new DBHelper();
			ResultSet rs = db.QueryCourseDetailsByCode(code);
			// if(rs==null) System.out.println("null");
			ArrayList al = null;
			ArrayList courselist = new ArrayList();
			try {
				System.out.println("cole");
				while (rs.next()) {
					al = new ArrayList();

//            System.out.println(rs.getString("code"));
//            System.out.println(rs.getString("title"));
//            System.out.println(rs.getString("teachername"));
					// System.out.println(rs.getString(4));
					try {
						al.add(rs.getString("reg"));
						al.add(rs.getString("name"));
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
			request.setAttribute("courselist", courselist);
			RequestDispatcher view = request.getRequestDispatcher("CourseDetails.jsp");
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
