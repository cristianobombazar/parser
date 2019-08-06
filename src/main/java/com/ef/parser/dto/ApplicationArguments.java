package com.ef.parser.dto;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.ef.parser.enuns.DurationTime;
import com.ef.parser.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Parameters(separators = "=")
public class ApplicationArguments {

    @Parameter(names = "--startDate", converter = LocalDateTimeConverter.class)
    private LocalDateTime startDate;

    @Parameter(names = "--duration", converter = DurationTimeConverter.class)
    private DurationTime duration;

    @Parameter(names = "--threshold")
    private Long threshold;

    @Parameter(names = "--accesslog")
    private String accessLogPath;

    private static class LocalDateTimeConverter implements IStringConverter<LocalDateTime> {
        @Override
        public LocalDateTime convert(String date) {
            return DateUtil.toDate(date, DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss"));
        }
    }

    private static class DurationTimeConverter implements IStringConverter<DurationTime> {
        @Override
        public DurationTime convert(String duration) {
            return DurationTime.valueOf(duration.toUpperCase());
        }
    }
}