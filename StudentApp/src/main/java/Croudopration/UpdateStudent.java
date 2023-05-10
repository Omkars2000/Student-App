package Croudopration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/newlink")
public class UpdateStudent extends HttpServlet {

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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String studid=req.getParameter("studentid");
		String name=req.getParameter("studentname");
		String stream=req.getParameter("studentstream");
		int id= Integer.parseInt(studid);

		
       int count=0;
		String qry="update student_app_info set stud_name=(?),stud_stream=(?) where stud_id=(?)";
		PreparedStatement pstmt=null;
		
		try {
				pstmt=con.prepareStatement(qry);
				
				pstmt.setString(1, name);
				pstmt.setString(2, stream);
				pstmt.setInt(3, id);
				count=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter pw=resp.getWriter();
		pw.print("<h1>"+count+" RECORD UPDATED SUCCESSFULLY<h1>");
	}
}
