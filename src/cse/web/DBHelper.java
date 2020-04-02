package cse.web;

import java.sql.*;

import com.mysql.jdbc.Connection;

public class DBHelper {
 
	 final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
     final String DB_URL="jdbc:mysql://localhost/Ecademiadb";
     final String USER = "root";
     final String PASS = "51255400";
     // This method will create a student profile on database
     public void AddStudent(String password,String name,String reg,String email)
     {
    	 try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			String sql= "insert into student values('"+ password + "','" + name + "','" + reg + "','" + email + "');";
			//System.out.println(sql);
			stmt.executeUpdate(sql);
    	 } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
     }
     // This method will create a student profile on database
     public void AddTeacher(String id,String password,String name,String description,String email)
     {
    	 try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			String sql= "insert into teacher values('" + id + "','"+ password + "','" + name + "','" + description + "','" + email + "');";
			//System.out.println(sql);
			stmt.executeUpdate(sql);
    	 } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
     }
    // /method to register for a course
     public void RegisterForCourse(String code,String title,String teacherid,String teachername,String reg)
     {
    	 String name=QuerynameByReg(reg);
    	 try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			
			String sql= "insert into courseregistration values('"+ code + "','" + title + "','" + teacherid + "','" + teachername + "','" + reg + "','" + name + "');";
			//System.out.println(sql); 
			stmt.executeUpdate(sql);
    	 } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
     }
     //Method to add a new course
     public void addCourse(String code,String title,String teacherid,String teachername)
     {
    	 
    	 try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			
			String sql= "insert into course values('"+ code + "','" + title + "','" + teacherid + "','" + teachername + "');";
			//System.out.println(sql); 
			stmt.executeUpdate(sql);
    	 } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
     }
   //method to get all the teacher info 
     public ResultSet getTeacher()
     {
    	 ResultSet ret = null;
    	 try {
  			Class.forName("com.mysql.jdbc.Driver");
  			Connection conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
  			Statement stmt = conn.createStatement();
  			String sql= "select * from teacher;";
  			System.out.println(sql);
  			ret=stmt.executeQuery(sql);
      	 } catch (ClassNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} 
    	 return ret;
     }
     //method to get all the course info 
     public ResultSet CoursesToRegister(String reg)
     {
    	 ResultSet ret = null;
    	 try {
  			Class.forName("com.mysql.jdbc.Driver");
  			Connection conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
  			Statement stmt = conn.createStatement();
  			String sql= "select distinct code,title,teacherid,teachername " + "from course " + "where course.code not in (select code from courseregistration where reg = '"+ reg + "');";
  			System.out.println(sql);
  			ret=stmt.executeQuery(sql);
      	 } catch (ClassNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} 
    	 return ret;
     }
     // This method will check the login credintials of student from database
     public String LoginQuery(String reg)
     {
    	 
    	 String ret=null;
    	 try {
 			Class.forName("com.mysql.jdbc.Driver");
 			Connection conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
 			Statement stmt = conn.createStatement();
 			String sql= "select password from student where reg = '" + reg +"';";
 			System.out.println(sql);
 			ResultSet res=stmt.executeQuery(sql);
 			if(res.next())
 	    	 {
 	    		 ret=res.getString("password");
 	    	 }
     	 } catch (ClassNotFoundException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} 
		return ret;
     }
     //This method will return hash password from database of a teacher id. 
     public String LoginQueryTeacher(String id)
     {
    	 
    	 String ret=null;
    	 try {
 			Class.forName("com.mysql.jdbc.Driver");
 			Connection conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
 			Statement stmt = conn.createStatement();
 			String sql= "select password from teacher where id = '" + id +"';";
 			System.out.println(sql);
 			ResultSet res=stmt.executeQuery(sql);
 			if(res.next())
 	    	 {
 	    		 ret=res.getString("password");
 	    	 }
     	 } catch (ClassNotFoundException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} 
		return ret;
     }
     //This method will return hash password from database of a admin id. 
     public String LoginQueryAdmin(String username)
     {
    	 
    	 String ret=null;
    	 try {
 			Class.forName("com.mysql.jdbc.Driver");
 			Connection conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
 			Statement stmt = conn.createStatement();
 			String sql= "select password from admin where username = '" + username +"';";
 			System.out.println(sql);
 			ResultSet res=stmt.executeQuery(sql);
 			if(res.next())
 	    	 {
 	    		 ret=res.getString("password");
 	    	 }
     	 } catch (ClassNotFoundException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} 
		return ret;
     }
     //     This method will query profile name based on the reg num from the database
     public String QuerynameByReg(String reg)
     {
    	 ResultSet ret = null;
    	 String nm=null;
    	 try {
  			Class.forName("com.mysql.jdbc.Driver");
  			Connection conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
  			Statement stmt = conn.createStatement();
  			String sql= "select name from student where reg = '"+ reg + "';";
  			System.out.println(sql);
  			ret=stmt.executeQuery(sql);
  			
  			if(ret.next())
  	    	 {
  	    		 nm=ret.getString("name");
  	    	 }
      	 } catch (ClassNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} 
    	 return nm;
     }
     //method to query courses of a student 
     public ResultSet QueryCourseByReg(String reg)
     {
    	 ResultSet ret = null;
    	 try {
  			Class.forName("com.mysql.jdbc.Driver");
  			Connection conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
  			Statement stmt = conn.createStatement();
  			String sql= "select * from courseregistration where reg = '"+ reg + "';";
  			//System.out.println(sql);
  			ret=stmt.executeQuery(sql);
//  			if(ret.next())
//  	    	 {
//  	    		 reg=ret.getString("code");
//  	    		 System.out.println(reg);
//  	    	 }
      	 } catch (ClassNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} 
    	// if(ret==null) System.out.println("null");
    	 return ret;
     }
     //Method to Query all the courses taken by a teacher
     public ResultSet QueryCourseByTeacherId(String id)
     {
    	 ResultSet ret = null;
    	 try {
  			Class.forName("com.mysql.jdbc.Driver");
  			Connection conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
  			Statement stmt = conn.createStatement();
  			String sql= "select * from course where teacherid = '"+ id + "';";
  			//System.out.println(sql);
  			ret=stmt.executeQuery(sql);
//  			if(ret.next())
//  	    	 {
//  	    		 reg=ret.getString("code");
//  	    		 System.out.println(reg);
//  	    	 }
      	 } catch (ClassNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} 
    	// if(ret==null) System.out.println("null");
    	 return ret;
     }
     public ResultSet QueryCourseDetailsByCode(String code)
     {
    	 ResultSet ret = null;
    	 try {
  			Class.forName("com.mysql.jdbc.Driver");
  			Connection conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
  			Statement stmt = conn.createStatement();
  			String sql= "select * from courseregistration where code = '"+ code + "';";
  			//System.out.println(sql);
  			ret=stmt.executeQuery(sql);
//  			if(ret.next())
//  	    	 {
//  	    		 reg=ret.getString("code");
//  	    		 System.out.println(reg);
//  	    	 }
      	 } catch (ClassNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} 
    	// if(ret==null) System.out.println("null");
    	 return ret;
     }
}
