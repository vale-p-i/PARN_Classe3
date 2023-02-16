package utils.ImageManager;

import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

public class ImageManager {

    private String rootPath, mail, fileExtension;
    private static String subfolder = "resource/pictures/";
    private Part filePart;

    public ImageManager(String rootPath, String mail, Part filePart, String fileExtention) {
        this.rootPath = rootPath;
        this.mail = mail;
        this.filePart = filePart;
        this.fileExtension = fileExtention;
    }

    public String saveImage() throws IOException {
        String saveDir = createDirectory();
        saveFileInDirectory(saveDir);
        return "resources/pictures/"+mail+"/image"+fileExtension;
    }

    private void saveFileInDirectory(String saveDir){
        try {
            filePart.write(saveDir + "/picture.jpg");
        } catch (IOException e) {
            System.err.println("IMPOSSIBILE SALVARE IMMAGINE IN: "+saveDir+"/picture.jpg");
        }
    }

    private String createDirectory() throws IOException {
        File folder = new File(rootPath+subfolder+ mail);
        boolean success = folder.mkdirs();
        if (success)
            return rootPath+subfolder+ mail;
        else
            throw new IOException("CANNOT CREATE FOLDER IN PATH: "+rootPath+subfolder+ mail);
    }

}
