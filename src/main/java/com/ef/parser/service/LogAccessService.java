package com.ef.parser.service;

import com.ef.parser.model.LogAccess;
import com.ef.parser.repository.LogAccessRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogAccessService {

    private final LogAccessRepository repository;

    public LogAccessService(LogAccessRepository repository) {
        this.repository = repository;
    }

    public LogAccess save(LogAccess logAccess) {
        return repository.save(logAccess);
    }

    public List<LogAccess> findAllByIpAndDataAndDateAccess(LocalDateTime startDate, LocalDateTime endDate, String ip) {
        return repository.findAllByIpAndDataAndDateAccess(startDate, endDate, ip);
    }

    public Map<String, List<LogAccess>> findAllIpsOverLimit(LocalDateTime startDate, LocalDateTime endDate, Long threshold) {
        Map<String, List<LogAccess>> mapIpAccess = new HashMap<>();
        List<Object[]> result = repository.findAllByDateAccessAndThresholdLimit(startDate, endDate, threshold);
        if (result != null && !result.isEmpty()){
            result.forEach(data -> {
                String  ip = (String) data[0];
                List<LogAccess> logAccesses = findAllByIpAndDataAndDateAccess(startDate, endDate, ip);
                mapIpAccess.put(ip, logAccesses);
            });
        }
        return mapIpAccess;
    }

}
