package com.backened;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mysql.cj.exceptions.DataReadException;



/**
 * Servlet implementation class DataLoading
 */
@WebServlet("/DataLoading")
public class DataLoading extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataLoading() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,DataReadException {
		
		try {
			HashMap<Object,Object> Response=new HashMap<Object,Object>();
			ArrayList<data> records= new ArrayList<data>();
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection com=DriverManager.getConnection("jdbc:mysql://localhost:3306/grey_goose?zeroDateTimeBehavior=round","root","112358");
			String sql_statement="select * from winter_internship";
			PreparedStatement ps=com.prepareStatement(sql_statement);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				data obj=new data(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getDate(5).toString().substring(0, 4),rs.getString(6),rs.getString(7),rs.getString(8),
						rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),
						rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16),rs.getString(17));
				records.add(obj);
			}
			Response.put("records", records);
			Gson gson=new Gson();
			String json=gson.toJson(Response);
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.getWriter().append(json);
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
