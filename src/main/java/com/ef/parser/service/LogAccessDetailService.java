package com.ef.parser.service;

import com.ef.parser.model.LogAccessDetail;
import com.ef.parser.repository.LogAccessDetailRepository;
import org.springframework.stereotype.Service;

@Service
public class LogAccessDetailService {

    private LogAccessDetailRepository repository;

    public LogAccessDetailService(LogAccessDetailRepository repository) {
        this.repository = repository;
    }

    public LogAccessDetail save(LogAccessDetail logAccessDetail) {
        return repository.save(logAccessDetail);
    }
}
