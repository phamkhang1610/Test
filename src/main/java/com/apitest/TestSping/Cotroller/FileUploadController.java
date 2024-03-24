package com.apitest.TestSping.Cotroller;

import com.apitest.TestSping.SeviceImpl.UploadFileServiceImpl;
import com.apitest.TestSping.entity.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
            //save file to
        }catch (Exception e){

        }
    }
}
