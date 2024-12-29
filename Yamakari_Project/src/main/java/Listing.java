import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listing")
public class Listing extends HttpServlet{
	static String name;
	static int price;
	static String category;
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	 throws ServletException, IOException {
		name = req.getParameter("name");
		price = Integer.parseInt(req.getParameter("price"));
		category = req.getParameter("category");
		
		req.setAttribute("name", name);
		req.setAttribute("price", price);
		req.setAttribute("category", category);
		
		RequestDispatcher rd = req.getRequestDispatcher("/view/listing.jsp");
		rd.forward(req, res);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			 throws IOException, ServletException {
				doPost(req, res);
			}
}
