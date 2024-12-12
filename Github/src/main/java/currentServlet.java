import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/src/main/java/current")
public class currentServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	 throws IOException, ServletException{
		String projectRoot = System.getProperty("user.dir");
		
		PrintWriter out = res.getWriter();
		out.println("<HTML>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>結果</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>" + projectRoot + "</h2>");
		out.println("</HTML>");
	}
}
