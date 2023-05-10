package Croudopration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



@WebServlet("/addlink")
public class AddStudent extends HttpServlet {
      Connection con=null;
	 @Override
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String studid=req.getParameter("studentid");
		String name=req.getParameter("studentname");
		String stream=req.getParameter("studentstream");
		int id= Integer.parseInt(studid);
        String date=req.getParameter("studentdate");
       
         java.sql.Date dob=java.sql.Date.valueOf(date);

        int count=0;
		String qry="insert into student_app_info values(?,?,?,?)";
		PreparedStatement pstmt=null;
		
		try {
				pstmt=con.prepareStatement(qry);
				pstmt.setInt(1, id);
				pstmt.setString(2, name);
				pstmt.setString(3, stream);
				pstmt.setString(4, date);;
				count=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter pw=resp.getWriter();
		pw.print("<h1>"+count+" RECORD INSERTED SUCCESSFULLY<h1>");
	}

	
	

}
