import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Login extends HttpServlet {
	static int userID;
	static String name;
	public void doPost(HttpServletRequest req, HttpServletResponse res)
	 throws ServletException, IOException {
		name = req.getParameter("account");
		String pass = req.getParameter("pass");
		
		AccountDAO adao = new AccountDAO();
		
		String resultPass = adao.getPass(name);
		
		RequestDispatcher rd;
		if(resultPass != null && pass.equals(resultPass)) {
			userID = adao.getID(name);
			rd = req.getRequestDispatcher("/index");
			rd.forward(req, res);
		} else {
			String msg = "入力情報に誤りがあります";
			rd = req.getRequestDispatcher("/view/login.jsp");
			req.setAttribute("msg", msg);
			rd.forward(req, res);
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			 throws IOException, ServletException {
				doPost(req, res);
			}
}
