package com.ef.parser.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {

    public static File create(String pathFile) throws IOException {
        File file = new File(pathFile);
        if (file.exists()){
            file.delete();
        }
        file.createNewFile();
        return file;
    }


    public static List<String> readAllLines(String fileName) throws IOException {
        Resource resource = new ClassPathResource(fileName);
        if (resource.isReadable()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            return reader.lines().collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
