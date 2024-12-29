import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.GoodsDTO;

@WebServlet("/index")
public class Index extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	 throws ServletException, IOException{
		
		String msg;
		AccountDAO adao = new AccountDAO();
		int money = adao.getMoney(Login.userID);
		
		//チャージ
		String checkIncomPoint = req.getParameter("incomPoint");
		int incomPoint = 0;
		System.out.println("チャージ額: " + checkIncomPoint);
		if(checkIncomPoint != null) {
			money = adao.getMoney(Login.userID);
			incomPoint = Integer.parseInt(checkIncomPoint);
			money += incomPoint;
			System.out.println("money: " + money);
			adao.updateMoney(money, Login.userID);
			checkIncomPoint = null;
		}

		GoodsDAO gdao = new GoodsDAO();
		//　購入
        String productID = req.getParameter("product_id");
        if(productID != null) {
        	int id = Integer.parseInt(productID.split("_")[1]);
        	int price = gdao.getPrice(id);
        	if(money - price >= 0) {
        		//購入者
	        	money -= price;
	        	adao.updateMoney(money, Login.userID);
	        	//出品者
	        	int sellerID = gdao.getUserID(id);
	        	int sellerMoney = adao.getMoney(sellerID) + price;
	        	adao.updateMoney(sellerMoney, sellerID);
	        	
	        	Upload.deleteFile(gdao.getFileName(id));
	        	gdao.delete(id);
	        	productID = null;
	        	
	        	GoodsDTO gdto = gdao.selectGoods();
	    		
	    		req.setAttribute("name", Login.name);
	    		req.setAttribute("money", money);
	    		req.setAttribute("gdto", gdto);
	    		RequestDispatcher rd = req.getRequestDispatcher("/view/index.jsp");
	    		rd.forward(req, res);
	    		
        	} else {
        		msg = "残高不足のため購入できませんでした";
        		req.setAttribute("msg", msg);
        		RequestDispatcher rd = req.getRequestDispatcher("/view/charge.jsp");
        		rd.forward(req, res);
        	}
        } else {
        	GoodsDTO gdto = gdao.selectGoods();
    		
    		req.setAttribute("name", Login.name);
    		req.setAttribute("money", adao.getMoney(Login.userID));
    		req.setAttribute("gdto", gdto);
    		RequestDispatcher rd = req.getRequestDispatcher("/view/index.jsp");
    		rd.forward(req, res);
        }
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
	 throws ServletException, IOException{
		doPost(req, res);
	}
}
