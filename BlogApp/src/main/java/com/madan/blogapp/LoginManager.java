package com.madan.blogapp;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;


public class LoginManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "jdbc:mysql://localhost:3306/madandb";
		String id = "root";
		String pas = "Madanmohan#2000";
		
		RequestDispatcher rd;
		
		//data about form
		String email = request.getParameter("Uemail");
		String password = request.getParameter("upwd");
		
		//database connection->
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, id, pas);
			PreparedStatement pst = con.prepareStatement("select * from blogApp where email like binary ? and password like binary ?");
			pst.setString(1,email);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				rd =  request.getRequestDispatcher("./Home/index.html");
				rd.forward(request, response);
			}
			else {
				rd = request.getRequestDispatcher("loginError.jsp");
				rd.forward(request, response);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
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
