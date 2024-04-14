package com.apitest.TestSping.Service;

import com.apitest.TestSping.SeviceImpl.UploadFileServiceImpl;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
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
            float fileSize = (float) File.getSize() / 1000000.0f;
            if(fileSize > 5.0f){
                throw new RuntimeException("file must be <= 5mb");
            }
            // đổi tên file ảnh( do sợ trùng tên file đã đến mất data)
            String fileExtension = FilenameUtils.getExtension(File.getOriginalFilename());
            String generatedFileName = UUID.randomUUID().toString().replace("-","");
            generatedFileName = generatedFileName+"."+fileExtension;// tạo tên file random để ko bị trùng tên file
            Path detinationFilePath = this.storageFolder.resolve(
                    Paths.get(generatedFileName))
                            .normalize().toAbsolutePath();
            if(!detinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath())){
                throw new RuntimeException("Cannot store file outside current directory");
            }
            try (InputStream inputStream = File.getInputStream()){
                Files.copy(inputStream,detinationFilePath, StandardCopyOption.REPLACE_EXISTING);// copy file ra
            }
            return generatedFileName;
        } catch (IOException e) {
            throw new RuntimeException("cannot initialize storage", e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
       try{
           return Files.walk(this.storageFolder,1)
                   .filter(path -> !path.equals(this.storageFolder))
                   .map(this.storageFolder::relativize);
       }catch (IOException e){
           throw  new RuntimeException("Failed to lod stored files",e);
       }
    }

    @Override
    public byte[] readFileContent(String fileName) {
        try{
            Path file = storageFolder.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists()||resource.isReadable()){
                byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
                return bytes;
            }
            else {
                throw new RuntimeException("Could not read file"+ fileName);
            }
        }catch (IOException e){
            throw new RuntimeException("Could ot read file:"+fileName,e);
        }
    }

    @Override
    public void deletaAllFile() {

    }
}
