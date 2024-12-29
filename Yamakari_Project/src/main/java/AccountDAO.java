import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDAO {
	//accountテーブルを作成してください
	final String URL = "jdbc:mysql://localhost/YamakariDB";
	final String USER = "";
	final String PASS = "";
		
	Connection con = null;
	
	protected void connect() {
		try {
			con = DriverManager.getConnection(URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int countData() {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM account";
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
	
	public String insertAccount(String name, String pass) {
		String result = "アカウント登録が完了しました！";
		connect();
		try (PreparedStatement pstmt = con.prepareStatement("INSERT INTO account VALUES(?,?,?,?)")){
			pstmt.setInt(1, countData()+1);
			pstmt.setString(2, name);
			pstmt.setString(3, pass);
			pstmt.setInt(4, 0);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			if (e.getSQLState().equals("23000")) {
		        result = ("この名前はすでに存在します！");
			}
		}
		disconnect();
		return result;
	}
	
	public String getPass(String name) {
		ResultSet rs = null;
		String sql = "SELECT * FROM account WHERE name=?";
		String result = null;
		connect();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getString("pass");
		} catch (Exception e) {
			System.out.println("※アカウント名: " + name + "は登録されていません");
		} finally {
			try {
				if (rs != null) rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		disconnect();
		return result;
	}
	
	public int getID(String name) {
		ResultSet rs = null;
		String sql ="SELECT * FROM account WHERE name=?";
		int result = 0;
		connect();
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt("id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int getMoney(int id) {
		ResultSet rs = null;
		String sql = "SELECT * FROM account WHERE id=?";
		int result = 0;
		connect();
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt("money");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void updateMoney(int money, int id) {
		connect();
		try (PreparedStatement pstmt = con.prepareStatement("UPDATE account SET money=? WHERE id=?")){
			pstmt.setInt(1, money);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		disconnect();
	}
	
	protected void disconnect() {
		try {
			if(con != null) con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
