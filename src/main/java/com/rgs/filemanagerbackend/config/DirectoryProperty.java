package com.rgs.filemanagerbackend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "directory-property")
@Data
public class DirectoryProperty {
    private final Map<String, Directory> directory= new HashMap<>();
    @Data
    public static class Directory {
        private String path;
        private boolean access;
    }
}
