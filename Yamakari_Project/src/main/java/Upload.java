import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig
public class Upload extends HttpServlet {
    private static final int FIXED_WIDTH = 300; // 固定幅
    private static final int FIXED_HEIGHT = 270; // 固定高さ

    public void doPost(HttpServletRequest req, HttpServletResponse res) 
     throws ServletException, IOException {
        Part filePart = req.getPart("image");
        String originalFileName = getFileName(filePart);
        String msg;

        if (originalFileName != null && !originalFileName.isEmpty()) {
            // プロジェクトのルートディレクトリのパスを指定
            String projectPath = ""; 
            String uploadPath = projectPath + "/src/main/webapp/view/img";

            // 保存先のディレクトリを作成
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs(); // ディレクトリが存在しない場合は作成する
            }

            // ユニークなファイル名を生成
            String uniqueFileName = generateUniqueFileName(uploadDir, originalFileName);
            File savedFile = new File(uploadDir, uniqueFileName);

            try (InputStream fileContent = filePart.getInputStream()) {
                // ファイルをコピー
                Files.copy(fileContent, savedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // リサイズ処理
                resizeImage(savedFile, FIXED_WIDTH, FIXED_HEIGHT);

                msg = "アップロード成功. 出品情報が確定されました";
                req.setAttribute("fileName", uniqueFileName); // 必要ならファイル名をリクエストに追加
            } catch (Exception e) {
                msg = "アップロード失敗.やり直してください";
                req.setAttribute("msg", msg);
                RequestDispatcher rd = req.getRequestDispatcher("/view/listing.jsp");
                rd.forward(req, res);
                e.printStackTrace();
                return;
            }
        } else {
            msg = "アップロード失敗.やり直してください";
            req.setAttribute("msg", msg);
            RequestDispatcher rd = req.getRequestDispatcher("/view/listing.jsp");
            rd.forward(req, res);
            return;
        }
        System.out.println(msg);

        // DBへ格納
        GoodsDAO gdao = new GoodsDAO();
        gdao.insertGoods(Listing.name, Listing.price, Listing.category, originalFileName, Login.userID);

        RequestDispatcher rd = req.getRequestDispatcher("/index");
        rd.forward(req, res);
    }

    private void resizeImage(File inputFile, int width, int height) throws IOException {
        BufferedImage originalImage = ImageIO.read(inputFile);
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        g2d.dispose();
        ImageIO.write(resizedImage, "jpg", inputFile); // 上書き保存
    }

    private String generateUniqueFileName(File uploadDir, String originalFileName) {
        String name = originalFileName.substring(0, originalFileName.lastIndexOf('.'));
        String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        File file = new File(uploadDir, originalFileName);

        int count = 1;
        while (file.exists()) {
            String newName = name + "_" + count + extension;
            file = new File(uploadDir, newName);
            count++;
        }
        return file.getName();
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 2, content.length() - 1);
            }
        }
        return null;
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) 
     throws IOException, ServletException {
        doPost(req, res);
    }

    public static void deleteFile(String fileName) {
        // プロジェクトのルートディレクトリのパスを指定
        String projectPath = ""; 
        String uploadPath = projectPath + "/src/main/webapp/view/img";
        
        // 削除するファイルのパス
        File fileToDelete = new File(uploadPath, fileName);
        
        // ファイルが存在し、削除できる場合
        if (fileToDelete.exists()) {
            if (fileToDelete.delete()) {
                System.out.println("ファイル " + fileName + " を削除しました。");
            } else {
                System.out.println("ファイル " + fileName + " の削除に失敗しました。");
            }
        } else {
            System.out.println("指定されたファイルは存在しません: " + fileName);
        }
    }
}


