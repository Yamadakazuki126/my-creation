import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
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
    String msg = "生徒全員の情報を表示します";
    //入力値（btn）を取得
    req.setCharacterEncoding("utf-8");
    String btn = req.getParameter("btn");
    //StudentDAOオブジェクトを生成
    StudentDAO3 sdao = new StudentDAO3();
    //押下ボタンによる分岐処理
    if(btn.equals("追加")){
      int no = Integer.parseInt(req.getParameter("no"));
      String name = req.getParameter("name");
      int score = Integer.parseInt(req.getParameter("score"));
      sdao.insert(no, name, score);
      msg = "番号" + no + "の生徒を追加しました";
    } else if(btn.equals("修正")) {
      int no = Integer.parseInt(req.getParameter("no"));
      String name = req.getParameter("name");
      int score = Integer.parseInt(req.getParameter("score"));
      sdao.update(no, name, score);
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
    RequestDispatcher rd = req.getRequestDispatcher("/dbweb/editstudent.jsp");
    rd.forward(req, res);
  }

  public void doGet(HttpServletRequest req, HttpServletResponse res)
      throws IOException, ServletException {
    doPost(req, res);
  }
}
