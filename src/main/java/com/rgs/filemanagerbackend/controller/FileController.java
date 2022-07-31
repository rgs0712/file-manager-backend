package com.rgs.filemanagerbackend.controller;

import com.rgs.filemanagerbackend.exception.FileManagerException;
import com.rgs.filemanagerbackend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/directory")
public class FileController {
    @Autowired
    private FileService fileService;
    @GetMapping("{directoryKey}/files")
    public ResponseEntity<?> listFiles(@PathVariable(name = "directoryKey") final String directoryKey){
        return ResponseEntity.ok(fileService.listAllFilesByDirectoryKey(directoryKey));
    }
    @GetMapping("/list")
    public ResponseEntity<?> listDirs(){
        return ResponseEntity.ok(fileService.listAllDirs());
    }
    @GetMapping("/download/file")
    public ResponseEntity<Resource> download(
            @RequestParam final String directoryKey,
            @RequestParam final String fileName) throws IOException{

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
        Resource fileToDownload = fileService.getFileToDownload(directoryKey, fileName);
        return ResponseEntity.ok()
                .headers(header)
                .contentLength(fileToDownload.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileToDownload);
    }
    @PostMapping("upload/file")
    public ResponseEntity<?> uploadFile(@RequestParam String directoryKey, @RequestParam MultipartFile file) throws IOException {
        fileService.uploadFile(directoryKey, file);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
}
