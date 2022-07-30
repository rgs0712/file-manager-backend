package com.rgs.filemanagerbackend.controller;

import com.rgs.filemanagerbackend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
