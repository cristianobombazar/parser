package com.ef.parser.runner;

import com.beust.jcommander.JCommander;
import com.ef.parser.dto.ApplicationArguments;
import com.ef.parser.service.ParserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private final ParserService parserService;

    public ApplicationRunner(ParserService parserService) {
        this.parserService = parserService;
    }

    @Override
    public void run(String... args) throws Exception {
        ApplicationArguments applicationArguments = ApplicationArguments.builder().build();
        try {
            JCommander.newBuilder().addObject(applicationArguments).build().parseWithoutValidation(args);
            Objects.requireNonNull(applicationArguments.getDuration(), "Duration must be not null");
            Objects.requireNonNull(applicationArguments.getThreshold(), "Threshold must be not null");
            Objects.requireNonNull(applicationArguments.getStartDate(), "StartDate must be not null");
            Objects.requireNonNull(applicationArguments.getAccessLogPath(), "AccessLog path must be not nul");
        }catch (Exception e){
            System.out.println("It seems that your command line is invalid. Please, check and try it again.");
            throw e;
        }
        parserService.parse(applicationArguments);
    }
}
