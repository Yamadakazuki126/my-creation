import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import bean.StudentBean;
import bean.StudentDTO;

public class StudentDAO3 {
  private final String URL = "jdbc:sqlite:school/school_db.sqlite";
  private Connection con = null;
  private int count;

  public void connect(){
    try{
      //㈰DBに接続
      con = DriverManager.getConnection(URL);
    } catch(Exception e){
      e.printStackTrace();
    }
  }
  
  public void count() {
	  Statement stmt = null;
	  ResultSet rs = null;
	  StudentDTO sdto = new StudentDTO();
	  String sql = "SELECT COUNT(*) FROM student";
	  try {
		  connect();
		  stmt = con.createStatement();
		  rs = stmt.executeQuery(sql);
		  count = rs.getInt(1);
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
	  disconnect();
  }
  
  public StudentDTO select() {
    Statement stmt = null;
    ResultSet rs = null;
    StudentDTO sdto = new StudentDTO();
    String sql = "SELECT * FROM student";
    try{
      connect();
      //㈪ステートメントを生成
      stmt = con.createStatement();
      //㈫SQLを実行
      rs = stmt.executeQuery(sql);
      //㈬検索結果の処理
      while(rs.next()){
        StudentBean sb = new StudentBean();
        sb.setNo(rs.getInt("no"));
        sb.setName(rs.getString("name"));
        sb.setRoom(rs.getString("room"));
        sb.setScore(rs.getInt("score"));
        sdto.add(sb);
      }
    } catch(Exception e){
      e.printStackTrace();
    } finally {
      try{
        if(rs != null) rs.close();
        if(stmt != null) stmt.close();
      } catch(Exception e){
        e.printStackTrace();
      }
    }
    disconnect();
    return sdto;
  }
  
  public boolean duplication(int no) {
	    String sql = "SELECT COUNT(*) FROM student WHERE no = ?"; 
	    try {
	    	connect();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setInt(1, no);
	        ResultSet rs = pstmt.executeQuery();
	        return rs.getInt(1) > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
  
  public int insert(int no, String name, String room, int score) {
	  	  count();
		  String sql = "INSERT INTO student VALUES (?, ?, ?, ?)";
		  try {
			  connect();
		  } catch(Exception e) {
			  e.printStackTrace();
		  }
		  try (PreparedStatement pstmt = con.prepareStatement(sql)) {
		      pstmt.setInt(1, no);
		      pstmt.setString(2, name);
		      pstmt.setString(3, room);
		      pstmt.setDouble(4, score);
		      pstmt.executeUpdate();
		  } catch(Exception e) {
			  e.printStackTrace();
		  }
		  disconnect();
		  return executeSql(sql);
  }
  
  public int update(int no, String name, String room, int score) {
	  String sql = "UPDATE student SET no = ?, name = ?, room = ?, score = ? WHERE no = ?";
	  try {
		  connect();
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
	  try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	      pstmt.setInt(1, no);
	      pstmt.setString(2, name);
	      pstmt.setString(3, room);
	      pstmt.setDouble(4, score);
	      pstmt.setInt(5, no);
	      pstmt.executeUpdate();
	  } catch (Exception e) {
	      e.printStackTrace();
	  }

    return executeSql(sql);
  }
  
  public int delete(int no) {
    String sql = "DELETE FROM student WHERE no = " + no;
    return executeSql(sql);
  }
  
  public int executeSql(String sql) {
    Statement stmt = null;
    ResultSet rs = null;
    int result = 0;
    try{
      connect();
      //㈪ステートメントを生成
      stmt = con.createStatement();
      //㈫SQLを実行
      result = stmt.executeUpdate(sql);
    } catch(Exception e){
      e.printStackTrace();
    } finally {
      try{
        if(rs != null) rs.close();
        if(stmt != null) stmt.close();
      } catch(Exception e){
        e.printStackTrace();
      }
    }
    disconnect();
    return result;
  }

  public void disconnect(){
    try{
      //㈭DBを切断
      if(con != null) con.close();
    } catch(Exception e){
      e.printStackTrace();
    }
  }
}

