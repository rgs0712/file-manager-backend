package com.rgs.filemanagerbackend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
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
}
