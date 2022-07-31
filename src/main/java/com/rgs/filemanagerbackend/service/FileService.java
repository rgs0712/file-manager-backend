package com.rgs.filemanagerbackend.service;

import com.rgs.filemanagerbackend.exception.FileManagerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileService {
    @Value("#{${file-manager.directory.list}}")
    private Map<String,String> listDirectories;

    public Map<String,String> listAllDirs(){
        return listDirectories;
    }
    public List<String> listAllFilesByDirectoryKey(String directoryKey){
        String directory = listDirectories.get(directoryKey);
        return listAllFilesInDirectory(directory);
    }

    private List<String> listAllFilesInDirectory(String directory){
        return Stream.of(new File(directory).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }

    public Resource getFileToDownload(String directoryKey, String fileName) throws FileManagerException, MalformedURLException {
        String directory = listDirectories.get(directoryKey);
        List<String> strings = listAllFilesByDirectoryKey(directoryKey);
        if(!strings.contains(fileName)){
            throw new FileManagerException("File not Found");
        }
        URI uri = new File(directory +"/"+ fileName).toPath().toUri();
        return new UrlResource(uri);
    }
    @Async
    public void uploadFile(String directoryKey, MultipartFile file) throws IOException {
        String directory = listDirectories.get(directoryKey);
        String fileName = file.getOriginalFilename();
        byte[] bytes = file.getBytes();
        Files.write(new File(directory +"/"+ fileName).toPath(), bytes);
    }
}
