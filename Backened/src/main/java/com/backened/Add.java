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

/**
 * Servlet implementation class Add
 */
@WebServlet("/Add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id=Integer.parseInt(request.getParameter("id"));
		    String BC=request.getParameter("BC");
			int CN=Integer.parseInt(request.getParameter("CN"));
			String CD=request.getParameter("CD");
			String BY=request.getParameter("BY");
			String Did=request.getParameter("Did");
			String PD=request.getParameter("PD");
			String DCD=request.getParameter("DCD");
			String DD=request.getParameter("DD");
			String IC=request.getParameter("IC");
			String DT=request.getParameter("DT");
			String Pid=request.getParameter("Pid");
			String TOM=request.getParameter("TOM");
			String BCD=request.getParameter("BCD");
			String CPT=request.getParameter("CPT");
			String Iid=request.getParameter("Iid");
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection com=DriverManager.getConnection("jdbc:mysql://localhost:3306/grey_goose?zeroDateTimeBehavior=round","root","112358");
			
			String sql_statement1="select * from customer where cust_number=?;";
			PreparedStatement ps=com.prepareStatement(sql_statement1);
			ps.setInt(1, CN);
			ResultSet rs1=ps.executeQuery();
			
			String sql_statement2="select * from business where business_code=?;";
		    ps=com.prepareStatement(sql_statement2);
		    ps.setString(1, BC);
			ResultSet rs2=ps.executeQuery();
			
			String sql_statement3="insert into customer(cust_number) value (?);";
			String sql_statement4="insert into business(business_code) value (?);";			
			String sql_statement5="insert into winter_internship(sl_no,business_code,cust_number,clear_date,buisness_year,doc_id,posting_date,document_create_date,due_in_date,invoice_currency,document_type,posting_id,total_open_amount,baseline_create_date,cust_payment_terms,invoice_id) value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			
			if(!rs1.next()) {
				ps=com.prepareStatement(sql_statement3);
				ps.setInt(1, CN);
				ps.executeUpdate();
			}
			
			if(!rs2.next()) {
				ps=com.prepareStatement(sql_statement4);
				ps.setString(1, BC);
				ps.executeUpdate();
			}
	
			ps=com.prepareStatement(sql_statement5);
			ps.setInt(1, id);
			ps.setString(2, BC);
			ps.setInt(3, CN);
			ps.setString(4, CD);
			ps.setString(5, BY);
			ps.setString(6,Did);
			ps.setString(7,PD);
			ps.setString(8,DCD);
			ps.setString(9, DD);
			ps.setString(10, IC);
			ps.setString(11,DT);
			ps.setString(12, Pid);
			ps.setString(13,TOM);
			ps.setString(14, BCD);
			ps.setString(15, CPT);
			ps.setString(16, Iid);
			
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
