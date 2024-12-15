import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.StudentDTO;

@WebServlet("/src/main/java/editStudent")
public class EditStudentServlet extends HttpServlet {
  public void doPost(HttpServletRequest req, HttpServletResponse res)
      throws IOException, ServletException {
	  String msg = null;
	  String[] userInput = new String[4];
	  //入力値（btn）を取得
	  req.setCharacterEncoding("utf-8");
	  String btn = req.getParameter("btn");
	  // 空白がない場合の処理
	  msg = "生徒全員の情報を表示します";
	  
    //StudentDAOオブジェクトを生成
    StudentDAO3 sdao = new StudentDAO3();
    //押下ボタンによる分岐処理
    if(btn.equals("追加")){
      int no = Integer.parseInt(req.getParameter("no"));
      String name = req.getParameter("name");
      String room = req.getParameter("room");
      int score = Integer.parseInt(req.getParameter("score"));
      //重複チェック
      if(sdao.duplication(no)) {
    	  msg = "番号" + no + "の生徒はすでに登録されています";
      }else {
    	  sdao.insert(no, name, room, score);
          msg = "番号" + no + "の生徒を追加しました";
      }
    } else if(btn.equals("修正")) {
      int no = Integer.parseInt(req.getParameter("no"));
      String name = req.getParameter("name");
      String room = req.getParameter("room");
      int score = Integer.parseInt(req.getParameter("score"));
      sdao.update(no, name, room, score);
      msg = "番号" + no + "の生徒を修正しました";
    } else if(btn.equals("削除")) {
      int no = Integer.parseInt(req.getParameter("no"));
      sdao.delete(no);
      msg = "番号" + no + "の生徒を削除しました";
    }
    //全件検索
    StudentDTO sdto = sdao.select();
    //リクエストスコープにDTOとmsgを格納
    req.setAttribute("sdto", sdto);
    req.setAttribute("msg", msg);
    //JSPにフォワード
    req.getRequestDispatcher("/dbweb/editstudent.jsp").forward(req, res);
  }

  public void doGet(HttpServletRequest req, HttpServletResponse res)
      throws IOException, ServletException {
    doPost(req, res);
  }
}