import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import bean.DTO;
import bean.dbBean;

public class DAO {
	 String dbPath = "book/book.db";  
     String url = "jdbc:sqlite:" + dbPath;
     private Connection conn = null;
     
         // データベースへの接続
         public void connect() {
        	 try {
	        	 // ドライバのロード
	             Class.forName("org.sqlite.JDBC");
		         conn = DriverManager.getConnection(url);
		         System.out.println("接続成功！");
        	 }catch (Exception e) {
        		 e.printStackTrace();
        	 }
         }

         // データを取得（SELECT）
         public DTO select() {
	         String selectSQL = "SELECT * FROM student";
	         DTO dto = new DTO();
	         Statement stmt = null;
	         ResultSet rs = null;
	         try {
		         connect();
		         stmt = conn.createStatement();
		         rs = stmt.executeQuery(selectSQL);
		         System.out.println("データベース内のデータ:");
		         while (rs.next()) {
		        	 dbBean db = new dbBean();
		             db.setID(rs.getInt("id"));
		             db.setName(rs.getString("name"));
		             db.setAge(rs.getInt("age"));
		             dto.add(db);
		         }
	         } catch (Exception e) {
	        	 e.printStackTrace();
	         } finally {
	        	 try {
	        		 if(rs != null) rs.close();
	        		 if(stmt != null) stmt.close();
	        	 } catch(Exception e) {
	        		 e.printStackTrace();
	        	 }
	         }
	         disconnect();
	         return dto;
         }
         
         public void disconnect() {
        	 try {
        		 if(conn != null) conn.close();
        	 }catch (Exception e){
        		 e.printStackTrace();
        	 }
         }
}
