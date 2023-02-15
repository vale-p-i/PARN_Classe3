package utils.ImageManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(
        name = "UploadImage",
        description = "Upload the image to the server",
        urlPatterns = {"/UploadImage"}
)
public class UploadImage extends HttpServlet {
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<p>Hello World!</p>");

        String rootPath = String.valueOf(request.getServletContext().getResource("/").getPath());
        String email = request.getParameter("email");
        Part imagePart = request.getPart("image");
        String fileExtention = imagePart.getSubmittedFileName().substring(imagePart.getSubmittedFileName().indexOf('.'));

        System.out.println("rootPath: "+rootPath);
        System.out.println("codiceFiscale: "+email);
        System.out.println("fileExt: "+fileExtention);

        ImageManager imageManager = new ImageManager(rootPath, email, imagePart, fileExtention);
        String resultUrl = imageManager.saveImage();

        System.out.println("ResultURL: "+resultUrl);

        response.sendRedirect("testUploadFoto.jsp");
    }
    @Override
    protected void doPost(HttpServletRequest request,
                            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
