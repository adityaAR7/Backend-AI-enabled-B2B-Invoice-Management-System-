package com.backened;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String selected[]=request.getParameterValues("selected");
			int ids[]=new int[selected.length];
			for(int i=0;i<selected.length;i++) {
				ids[i]=Integer.parseInt(selected[i]);
			}
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection com=DriverManager.getConnection("jdbc:mysql://localhost:3306/grey_goose?zeroDateTimeBehavior=round","root","112358");
			for(int i=0;i<selected.length;i++) {
				int id=ids[i];
				String sql_statement1="select * from winter_internship where sl_no=?;";
				PreparedStatement ps=com.prepareStatement(sql_statement1);
				ps.setInt(1, id);
				ResultSet rs=ps.executeQuery();
				rs.next();
				int CN=rs.getInt(3);
				String BC=rs.getString(2);
				
				String sql_statement2="delete from winter_internship where sl_no=?;";
				ps=com.prepareStatement(sql_statement2);
				ps.setInt(1, id);
				ps.executeUpdate();
				
				String sql_statement3="select * from winter_internship where cust_number=?;";
				ps=com.prepareStatement(sql_statement3);
				ps.setInt(1, CN);
				rs=ps.executeQuery();
				if(!rs.next()) {
					String sql_statement4="delete from customer where cust_number=?;";
					ps=com.prepareStatement(sql_statement4);
					ps.setInt(1, CN);
					ps.executeUpdate();
				}
				
				String sql_statement5="select * from winter_internship where business_code=?;";
				ps=com.prepareStatement(sql_statement5);
				ps.setString(1, BC);
				rs=ps.executeQuery();
				if(!rs.next()) {
					String sql_statement6="delete from business where business_code=?;";
					ps=com.prepareStatement(sql_statement6);
					ps.setString(1, BC);
					ps.executeUpdate();
				}
				
				
			}
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
