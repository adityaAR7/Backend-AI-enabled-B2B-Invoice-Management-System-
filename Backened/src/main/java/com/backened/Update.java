package com.backened;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			int id=Integer.parseInt(request.getParameter("id"));
			String CPT=request.getParameter("CPT");
			String IC=request.getParameter("IC");
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection com=DriverManager.getConnection("jdbc:mysql://localhost:3306/grey_goose?zeroDateTimeBehavior=round","root","112358");
			String sql_statement="update winter_internship set invoice_currency=?,cust_payment_terms=? where sl_no=?;";
			PreparedStatement ps=com.prepareStatement(sql_statement);
			ps.setString(1, IC);
			ps.setString(2, CPT);
			ps.setInt(3, id);
			ps.executeUpdate();
			
			response.setHeader("Access-Control-Allow-Origin", "*");
		}	
		catch(SQLException e1) {
			for(Throwable ec:e1) {
				if(ec instanceof SQLException) {
				ec.printStackTrace(System.err);
				System.err.println("SQLState: "+ ((SQLException) ec).getSQLState());
				System.err.println("Error code: "+ ((SQLException) ec).getErrorCode());
				System.err.println("Message: "+ec.getMessage());
				Throwable t=e1.getCause();
				while(t!=null) {
				System.out.println("Cause: "+t);
				t=t.getCause();
				}
			}
	    }
	} catch (ClassNotFoundException e) {
		System.out.println("Message1: "+e.getMessage());
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
