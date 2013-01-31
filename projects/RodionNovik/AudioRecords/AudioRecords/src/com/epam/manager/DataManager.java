package com.epam.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DataManager {
	private static Connection con;
	private static Statement st;
	
	static {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/myprojectdb", "root", "1234");
			st = con.createStatement();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getAllRecords(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		ResultSet rs = st.executeQuery("SELECT * FROM records");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<table width=\"70%\" border=\"1\">");
		out.print("<tr><td>Name</td><td>Author</td><td>Year</td><td>Style</td><td>Album</td></tr>");
		while(rs.next()){
			out.print("<tr><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getInt(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(7)+"</td></tr>");
		}
		out.print("</table>");
	}

	public static void searchRecords(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		int n=0;
		String query="SELECT * FROM records";
		if(!request.getParameterValues("searchingName")[0].equals(""))
		{
				query+=" WHERE Name=\""+request.getParameterValues("searchingName")[0]+"\"";
				n++;
		}
		if(!request.getParameterValues("searchingAuthor")[0].equals(""))
		{
			if(n==0)
			{
				query+=" WHERE Author=\""+request.getParameterValues("searchingAuthor")[0]+"\"";
				n++;
			}
			else
				query+=" AND Author=\""+request.getParameterValues("searchingAuthor")[0]+"\"";
		}
		if(!request.getParameterValues("searchingYear")[0].equals(""))
		{
			if(n==0){
				query+=" WHERE Year=\""+request.getParameterValues("searchingYear")[0]+"\"";
				n++;
			}
			else
				query+=" AND Year=\""+request.getParameterValues("searchingYear")[0]+"\"";
		}
		if(!request.getParameterValues("searchingStyle")[0].equals(""))
		{
			if(n==0){
				query+=" WHERE Style=\""+request.getParameterValues("searchingStyle")[0]+"\"";
				n++;
			}
			else
				query+=" AND Style=\""+request.getParameterValues("searchingStyle")[0]+"\"";
		}
		if(!request.getParameterValues("searchingAlbum")[0].equals(""))
		{
			if(n==0)
			{
				query+=" WHERE Album=\""+request.getParameterValues("searchingAlbum")[0]+"\"";
				n++;
			}
			else
				query+=" AND Album=\""+request.getParameterValues("searchingAlbum")[0]+"\"";
		}
/*
		ResultSet rs = st.executeQuery(query);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<table width=\"70%\" border=\"1\">");
		out.print("<tr><td>Name</td><td>Author</td><td>Year</td><td>Style</td><td>Album</td><td>Make the assessment</td></tr>");
		String scale="<input type=\"radio\" name=\"usersAssessment\" value=\"1\">1<input type=\"radio\" name=\"usersAssessment\" value=\"2\">2<input type=\"radio\" name=\"usersAssessment\" value=\"3\">3";
		while(rs.next()){
			String endTd = "<td><form action=\"Assessment\" mthod=\"post\"><input type=\"hidden\" name=\"recordId\" value=\""+rs.getString(1)+"\">"+scale+"<input type=\"submit\" value=\"Assessment\"></form></td>";
			out.print("<tr><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getInt(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(7)+"</td>"+endTd+"</tr>");
		}
		out.print("</table>");
*/
		ResultSet rs = st.executeQuery(query);
		String resultStr="";
		resultStr+="<table width=\"70%\" border=\"1\">";
		resultStr+="<tr><td>Name</td><td>Author</td><td>Year</td><td>Style</td><td>Album</td><td>Make the assessment</td></tr>";
		String scale="<input type=\"radio\" name=\"usersAssessment\" value=\"1\">1<input type=\"radio\" name=\"usersAssessment\" value=\"2\">2<input type=\"radio\" name=\"usersAssessment\" value=\"3\">3";
		while(rs.next()){
			String endTd = "<td><form action=\"Assessment\" mthod=\"post\"><input type=\"hidden\" name=\"recordId\" value=\""+rs.getString(1)+"\">"+scale+"<input type=\"submit\" value=\"Assessment\"></form></td>";
			resultStr+=("<tr><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getInt(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(7)+"</td>"+endTd+"</tr>");
		}
		resultStr+="</table>";
		request.setAttribute("df", resultStr);
	}

	public static void MakeAnAssessment(String recordId,
			String usersAssessment, String login) throws ClassNotFoundException, SQLException {
		st.executeUpdate("INSERT INTO assessments (Login, idRecord, assessment) VALUES (\""+login+"\",\""+recordId+"\",\""+usersAssessment+"\")");	
	}

}
