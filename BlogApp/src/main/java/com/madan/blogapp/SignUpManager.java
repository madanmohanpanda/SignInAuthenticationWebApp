package com.madan.blogapp;



import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import java.sql.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SignUpManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
		String url = "jdbc:mysql://localhost:3306/madandb";
		String user = "root";
		String pass = "Madanmohan#2000";
		
		//form details
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Connection con=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
			
			PreparedStatement pst = con.prepareStatement("insert into blogApp values(?,?,?)");
			pst.setString(1, name);
			pst.setString(2, email);
			pst.setString(3, password);
			int count = pst.executeUpdate();
			
			RequestDispatcher rd;
			
			if(count>0) {
				rd= request.getRequestDispatcher("sign.html");
				
			}
			else {
				rd= request.getRequestDispatcher("error.jsp");
			}
			rd.forward(request, response);
		}catch(Exception e)
		{
			RequestDispatcher rd;
			rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
			
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}

}
