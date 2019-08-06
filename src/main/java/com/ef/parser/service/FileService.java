package com.ef.parser.service;

import com.ef.parser.model.LogAccess;
import com.ef.parser.util.DateUtil;
import com.ef.parser.util.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class FileService {

    public List<LogAccess> read(String fileName) throws IOException {
        try {
            List<String> lines = FileUtils.readAllLines(fileName);
            if (!lines.isEmpty()){
                return lines.stream().map(line -> {
                    LogAccess.LogAccessBuilder builder = LogAccess.builder();
                    String[] data = line.split(Pattern.quote("|"));
                    builder.dateAccess(DateUtil.toDate(data[0]));
                    builder.ip(data[1]);
                    builder.request(data[2]);
                    builder.status(Integer.parseInt(data[3]));
                    builder.userAgent(data[4]);
                    return builder.build();
                }).collect(Collectors.toList());
            }
            return new ArrayList<>();
        }catch (IOException e){
            System.out.println("An IoException occurred. Exception: "+e.getLocalizedMessage());
            throw e;
        }
    }

    public void write(File file, List<LogAccess> logAccesses) throws IOException {
        //Date, IP, Request, Status, User Agent (pipe delimited, open the example file in text editor)
        List<String> lines = logAccesses.stream().map(logAccess -> {
            StringBuilder line = new StringBuilder(); //more legible than string concatenation...
            line.append(DateUtil.format(logAccess.getDateAccess())).append("|");
            line.append(logAccess.getIp()).append("|");
            line.append(logAccess.getRequest()).append("|");
            line.append(logAccess.getStatus()).append("|");
            line.append(logAccess.getUserAgent());
            return line.toString();
        }).collect(Collectors.toList());
        Files.write(file.toPath(), lines, StandardOpenOption.APPEND);
    }

}
