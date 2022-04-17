package com.shym.backend.service;

import com.shym.backend.exception.FileNullException;
import com.shym.backend.exception.FileTypeInappropriateException;
import com.shym.backend.model.RentalOffer;
import com.shym.backend.model.User;
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
    public void uploadOfferImage(RentalOffer offer, MultipartFile file) throws IOException, NoSuchAlgorithmException {
        if (file == null || file.getContentType() == null) throw new FileNullException();
        if(!file.getContentType().startsWith("image")){
            throw new FileTypeInappropriateException(file.getContentType().toLowerCase(),"image");
        }
        String documentName = MD5.getMD5Hash(offer.getDescription())+ "." + FileUtils.getExtension(file);
        String documentPath = fileConfig.getDirectory();
        offer.setImagePath(FileUtils.saveFile(file,documentPath, documentName));
    }

    public void uploadUserImage(User user, MultipartFile file) throws IOException, NoSuchAlgorithmException {
        if (file == null || file.getContentType() == null) throw new FileNullException();
        if(!file.getContentType().startsWith("image")){
            throw new FileTypeInappropriateException(file.getContentType().toLowerCase(),"image");
        }
        String documentName = MD5.getMD5Hash(user.getEmail())+ "." + FileUtils.getExtension(file);
        String documentPath = fileConfig.getDirectory();
        FileUtils.saveFile(file,documentPath, documentName);
        user.setImagePath(FileUtils.saveFile(file,documentPath, documentName));
    }
}
