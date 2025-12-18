package com.ecommerce.Ecommerce.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {

    public String uploadImage(String path, MultipartFile file) throws IOException {
        //get the file name current file
        String originalFileName = file.getOriginalFilename();

        //Generate unique file name
        String randomId = UUID.randomUUID().toString();
        String fileName= randomId.concat(originalFileName.substring(originalFileName.lastIndexOf('.')));
        //check if path exist and create
        String filePath = path + File.separator + fileName;
        File folder = new File(path);

        if(!folder.exists())
            folder.mkdir();

        //Upload the server

        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName;
    }

}
