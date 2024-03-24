package com.apitest.TestSping.Service;

import com.apitest.TestSping.SeviceImpl.UploadFileServiceImpl;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class UploadFileService implements UploadFileServiceImpl {
    private final Path storageFolder = Paths.get("uploads");
    public UploadFileService() {
        try {
            Files.createDirectories(storageFolder);
        } catch (IOException e) {
            throw new RuntimeException("cannot initialize storage", e);
        }
    }
    //kiểm tra xem file vào có phảo lài file ảnh không( thông qua đuôi file)
    private boolean isImageFile(MultipartFile file){
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        return Arrays.asList(new String[] {"png","jpg","jpeg","bnp"})
                .contains(fileExtension.trim().toLowerCase());
    }
    @Override
    public String storeFile(MultipartFile File) {
        try {
            System.out.println("haha");
            if(File.isEmpty()){
                throw new RuntimeException("File is empty, Falied");
            }
            // check xem có đúng là file ảnh không.
            if(!isImageFile(File)){
                throw new RuntimeException("this is not file image");
            }
            // check size file < 5mb
            float fileSize = (float) File.getSize() / 1000000;
            if(fileSize > 5.0f){
                throw new RuntimeException("file must be <= 5mb");
            }
            // đổi tên file ảnh( do sợ trùng tên file đã đến mất data)
            String fileExtension = FilenameUtils.getExtension(File.getOriginalFilename());
            String generatedFileName = UUID.randomUUID().toString().replace("-","");
            generatedFileName = generatedFileName+"."+fileExtension;
            Path detinationFilePath = this.storageFolder.resolve(
                    Paths.get(generatedFileName))
                            .normalize().toAbsolutePath();
            if(!detinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath())){
                throw new RuntimeException("Cannot store filr outside current directory");
            }
            try (InputStream inputStream = File.getInputStream()){
                Files.copy(inputStream,detinationFilePath, StandardCopyOption.REPLACE_EXISTING);
            }
            return generatedFileName;
        } catch (IOException e) {
            throw new RuntimeException("cannot initialize storage", e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public byte[] readFileContent(String fileName) {
        return new byte[0];
    }

    @Override
    public void deletaAllFile() {

    }
}
