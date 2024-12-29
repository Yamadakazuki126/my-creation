import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.GoodsBean;
import bean.GoodsDTO;

public class GoodsDAO extends AccountDAO {
	
	public String insertGoods(String name, int price, String category, String fileName, int userID) {
		String result = "商品の登録が完了しました！";
		connect();
		try (PreparedStatement pstmt = con.prepareStatement("INSERT INTO goods VALUES(?,?,?,?,?,?)")){
			pstmt.setInt(1, countData()+1);
			pstmt.setString(2, name);
			pstmt.setInt(3, price);
			pstmt.setString(4, category);
			pstmt.setString(5, fileName);
			pstmt.setInt(6, userID);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		disconnect();
		return result;
	}
	
	public GoodsDTO selectGoods() {
		Statement stmt = null;
		ResultSet rs = null;
		GoodsDTO gdto = new GoodsDTO();
		connect();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM goods");
			while(rs.next()) {
				GoodsBean gb = new GoodsBean();
				gb.setID(rs.getInt("id"));
				gb.setName(rs.getString("name"));
				gb.setPrice(rs.getInt("price"));
				gb.setCategory(rs.getString("category"));
				gb.setFileName(rs.getString("fileName"));
				gb.setUserID(rs.getInt("userID"));
				gdto.add(gb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gdto;
	}
	
	public String getFileName(int id) {
		String result = null;
		ResultSet rs = null;
		connect();
		try (PreparedStatement pstmt = con.prepareStatement("SELECT * FROM goods WHERE id=?")){
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getString("fileName");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int getPrice(int id) {
		ResultSet rs = null;
		String sql = "SELECT * FROM goods WHERE id=?";
		int result = 0;
		connect();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt("price");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int getUserID(int id) {
		ResultSet rs = null;
		String sql = "SELECT * FROM goods WHERE id=?";
		int result = 0;
		connect();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt("userID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void delete(int id) {
		connect();
		try (PreparedStatement pstmt = con.prepareStatement("DELETE FROM goods WHERE id=?")) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			updateID(id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		disconnect();
	}
	
	private void updateID(int id) {
		try (PreparedStatement pstmt = con.prepareStatement("UPDATE goods SET id = id-1 WHERE id > ?;")){
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private int countData() {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM goods";
		int result = 0;
		connect();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			result = rs.getInt(1);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
