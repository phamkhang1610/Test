package com.apitest.TestSping.SeviceImpl;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface UploadFileServiceImpl {
    public String storeFile(MultipartFile File);
    public Stream<Path> loadAll();// load all file inside folder
    public byte[] readFileContent(String fileName);
    public void deletaAllFile();

}
