package com.shym.backend.service;

import com.shym.backend.exception.FileNullException;
import com.shym.backend.exception.FileTypeInappropriateException;
import com.shym.backend.model.RentalOffer;
import com.shym.backend.utils.FileConfig;
import com.shym.backend.utils.FileUtils;
import com.shym.backend.utils.MD5;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;


@Service
@Transactional
@AllArgsConstructor
public class FileService {
    private final FileConfig fileConfig;

    public String uploadOfferImage(RentalOffer offer, MultipartFile file) throws IOException, NoSuchAlgorithmException {
        if (file == null || file.getContentType() == null) throw new FileNullException();
        if(!file.getContentType().startsWith("image") && !file.getContentType().startsWith("application/pdf")){
            throw new FileTypeInappropriateException(file.getContentType().toLowerCase(),"image","pdf");
        }
        String documentName = MD5.getMD5Hash(offer.getId())+ "." + FileUtils.getExtension(file);
        String documentPath = fileConfig.getDirectory();
        FileUtils.saveFile(file,documentPath, documentName);
        return documentName;

    }
}
