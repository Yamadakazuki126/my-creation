import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/account")
public class Account extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	 throws IOException, ServletException {
		String name = req.getParameter("account");
		String pass = req.getParameter("pass");
		String msg;
		
			AccountDAO adao = new AccountDAO();
			msg = adao.insertAccount(name, pass);
			req.setAttribute("msg", msg);
			RequestDispatcher rd = req.getRequestDispatcher("/view/account.jsp");
			rd.forward(req, res);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
	 throws IOException, ServletException {
		doPost(req, res);
	}
}