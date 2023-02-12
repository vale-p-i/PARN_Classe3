package utils.ImageManager;

import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

public class ImageManager {

    private String rootPath, codideFiscale, fileExtension;
    private static String subfolder = "pictures/";
    private Part filePart;

    public ImageManager(String rootPath, String codideFiscale, Part filePart, String fileExtention) {
        this.rootPath = rootPath;
        this.codideFiscale = codideFiscale;
        this.filePart = filePart;
        this.fileExtension = fileExtention;
    }

    public String saveImage() throws IOException {
        String saveDir = createDirectory();
        saveFileInDirectory(saveDir);
        return saveDir+"image"+fileExtension;
    }

    private void saveFileInDirectory(String saveDir) throws IOException {
        filePart.write(saveDir+"picture.jpg");
    }

    private String createDirectory() throws IOException {
        File folder = new File(rootPath+subfolder+codideFiscale);
        boolean success = folder.mkdirs();
        if (success)
            return folder.getAbsolutePath();
        else
            throw new IOException();
    }

}
