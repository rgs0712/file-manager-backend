package com.rgs.filemanagerbackend.service;

import com.rgs.filemanagerbackend.config.DirectoryProperty;
import com.rgs.filemanagerbackend.exception.FileManagerException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class FileService {
    /*@Value("#{${file-manager.directory.list}}")
    private Map<String,String> listDirectories;*/

    @Autowired
    DirectoryProperty directoryProperty;
    public DirectoryProperty listAllDirs(){
        return directoryProperty;
    }
    public List<String> listAllFilesByDirectoryKey(String directoryKey){
        String directory = directoryProperty.getDirectory().get(directoryKey).getPath();
        return listAllFilesInDirectory(directory);
    }

    private List<String> listAllFilesInDirectory(String directory){
        return Stream.of(new File(directory).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }
    @SneakyThrows
    public Resource getFileToDownload(String directoryKey, String fileName){
        String directory = directoryProperty.getDirectory().get(directoryKey).getPath();
        List<String> strings = listAllFilesByDirectoryKey(directoryKey);
        if(!strings.contains(fileName)){
            throw new FileManagerException("File not Found");
        }
        URI uri = new File(directory +"/"+ fileName).toPath().toUri();
        return new UrlResource(uri);
    }
    @Async
    public void uploadFile(String directoryKey, MultipartFile file) throws IOException {
        String directory = directoryProperty.getDirectory().get(directoryKey).getPath();
        String fileName = file.getOriginalFilename();
        byte[] bytes = file.getBytes();
        Files.write(new File(directory +"/"+ fileName).toPath(), bytes);
    }
}
