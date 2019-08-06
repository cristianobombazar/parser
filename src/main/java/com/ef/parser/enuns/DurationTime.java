package com.ef.parser.enuns;

import java.time.LocalDateTime;
import java.util.function.Function;

public enum DurationTime {
    HOURLY(targetDate -> {
        return targetDate.plusHours(1);
    }),
    DAILY(targetDate -> {
        return targetDate.plusHours(24);
    });

    private Function<LocalDateTime, LocalDateTime> function;

    DurationTime(Function<LocalDateTime, LocalDateTime> function) {
        this.function = function;
    }

    public LocalDateTime apply(LocalDateTime targetDate){
        return function.apply(targetDate);
    }

}
