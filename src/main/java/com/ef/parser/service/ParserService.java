package com.ef.parser.service;

import com.ef.parser.dto.ApplicationArguments;
import com.ef.parser.enuns.DurationTime;
import com.ef.parser.model.LogAccess;
import com.ef.parser.model.LogAccessDetail;
import com.ef.parser.util.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ParserService {

    private final FileService fileService;
    private final LogAccessService logAccessService;
    private final LogAccessDetailService logAccessDetailService;

    public ParserService(final FileService fileService, final LogAccessService logAccessService, final LogAccessDetailService logAccessDetailService) {
        this.fileService = fileService;
        this.logAccessService = logAccessService;
        this.logAccessDetailService = logAccessDetailService;
    }

    public void parse(ApplicationArguments applicationArguments) throws IOException {
        System.out.println("Inserting data...");
        insertData();
        System.out.println("Done.");
        LocalDateTime startDate = applicationArguments.getStartDate();
        LocalDateTime endDate   = applicationArguments.getDuration().apply(startDate);
        Map<String, List<LogAccess>> ipsOverLimit = logAccessService.findAllIpsOverLimit(startDate, endDate, applicationArguments.getThreshold());

        if (!ipsOverLimit.isEmpty()){
            File file = FileUtils.create(applicationArguments.getAccessLogPath()+"/accessLog.txt");
            for (Map.Entry<String, List<LogAccess>> data : ipsOverLimit.entrySet()) { //it was used just to throw exception up.
                String ip = data.getKey();
                List<LogAccess> logAccesses = data.getValue();

                LogAccessDetail.LogAccessDetailBuilder builder = LogAccessDetail.builder();
                builder.count(logAccesses.size());
                builder.startDate(startDate);
                builder.endDate(endDate);
                builder.ip(ip);
                if (applicationArguments.getDuration() == DurationTime.DAILY){
                    builder.reason("The ip '"+ip+"' has been blocked because it has exceeded the daily request number. Limit: "+applicationArguments.getThreshold());
                } else {
                    builder.reason("The ip  '"+ip+"' has been blocked because it has exceeded the hourly request number. Limit: "+applicationArguments.getThreshold());
                }
                logAccessDetailService.save(builder.build());
                fileService.write(file, logAccesses);
            }
        }
    }

    private void insertData() throws IOException {
        List<LogAccess> logs = fileService.read("static/access.log");
        if (!logs.isEmpty()) {
            logs.parallelStream().forEach(logAccessService::save);
        }
    }
}
