package com.shym.backend.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

@Controller

public class FileUtils {

    public static void saveFile(MultipartFile file, String documentPath, String documentName) throws IOException {
        if (!file.isEmpty()) {
            if (!new File(documentPath).exists()) {
                new File(documentPath).mkdirs();
            }
            String filePath = documentPath + documentName;
            File dest = new File(filePath);
//            File dest = new File(documentPath + "temp.pdf");
            file.transferTo(Paths.get(dest.getAbsolutePath()));
        }
    }

    public static String getExtension(MultipartFile file) {
        return Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".") + 1);
    }

    public static Object getFile(String image) {
        return new File("./data/file/"+image);
    }
}
