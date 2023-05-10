package Croudopration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/display.html")
public class DisplayStudent extends HttpServlet {
	Connection con=null;
	
	public void init() throws ServletException {
		 try {
			 //inside init becase of buld connection only once and the process will be done
			 Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja7","root", "sql@123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Statement stmt=null;
		ResultSet rs=null;
		PrintWriter pw=resp.getWriter();
		
		String query="select * from student_app_info";
		
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(query);
			pw.print("<table border='2'>");
			pw.print("<tr>");
			pw.print("<th>STUDENT ID</th>");
			pw.print("<th>STUDENT NAME</th>");
			pw.print("<th>STUDENT STREAM</th>");
			pw.print("</tr>");
			while(rs.next()) {
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String stream=rs.getString(3);
				pw.print("<tr>");
				pw.print("<td>"+id+"</td>");
				pw.print("<td>"+name+"</td>");
				pw.print("<td>"+stream+"</td>");
				pw.print("</tr>");
				
			}
			pw.print("</table>");
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
