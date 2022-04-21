package com.shym.backend.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Objects;


public class FileUtils {

    public static String saveFile(MultipartFile file, String documentPath, String documentName) throws IOException {
        if (!file.isEmpty()) {
            if (!new File(documentPath).exists()) {
                new File(documentPath).mkdirs();
            }
            String filePath = documentPath + documentName;
            File dest = new File(filePath);
//            File dest = new File(documentPath + "temp.pdf");
            file.transferTo(Paths.get(dest.getAbsolutePath()));
            return documentName;
        }
        return null;
    }

    public static String getExtension(MultipartFile file) {
        return Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".") + 1);
    }

    public static byte[] getFile(String image, String documentPath) throws IOException {
        File file = new File(documentPath + image);
        return fileToInputStream(file);
    }

    public static byte[] fileToInputStream(File file)
            throws IOException
    {
        FileInputStream fl = new FileInputStream(file);
        byte[] arr = new byte[(int)file.length()];
        fl.read(arr);
        fl.close();
        return arr;
    }
}
