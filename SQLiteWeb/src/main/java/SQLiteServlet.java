import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.DTO;

@WebServlet("/src/main/java/sqlite")
public class SQLiteServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
	 throws IOException, ServletException{
		DAO dao = new DAO();
		DTO dto = dao.select();
		req.setAttribute("dto", dto);
		RequestDispatcher rd = req.getRequestDispatcher("/dbweb/result.jsp");
		rd.forward(req, res);
    	}
	public void doGet(HttpServletRequest req, HttpServletResponse res)
	 throws IOException, ServletException{
		 doPost(req,res);
	}
}
