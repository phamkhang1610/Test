package com.apitest.TestSping.Cotroller;

import com.apitest.TestSping.SeviceImpl.UploadFileServiceImpl;
import com.apitest.TestSping.entity.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/uploadfile")

public class FileUploadController {
    // this controller reciever file/image upload from client
    //inject storage service here
    @Autowired
    private UploadFileServiceImpl storageService;
    @PostMapping("")
    public ResponseEntity<ResponseObject>uploadfile (@RequestParam("file")MultipartFile file){
        try{
            //save file to a folder => use a sevice
            String generateFilename = storageService.storeFile(file);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "uploadfile succexxfull", generateFilename)
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", e.getMessage(), "")
            );
        }
    }
    @GetMapping("/files/{fileName:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName){
        try {
            byte[] bytes = storageService.readFileContent(fileName);
            return  ResponseEntity
                    .ok()//  khi ko load đc nó sẽ trả ra nocontent
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }
    // get all file image
    @GetMapping("")
    public ResponseEntity<ResponseObject> getUploadedFiles() {
        try{
            List<String> urls = storageService.loadAll()
                    .map(path ->{
                        // converrt file name url(send request:""
                        String urlsPath = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "readDetailFile", path.getFileName().toString()).build().toUri().toString();
                        return  urlsPath;
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.ok(
                    new ResponseObject("ok", "List file successfully",urls));

        }catch (Exception e){
            return ResponseEntity.ok(
                    new ResponseObject("ok", "List file successfully",new String[]{}));
        }
    }
}
