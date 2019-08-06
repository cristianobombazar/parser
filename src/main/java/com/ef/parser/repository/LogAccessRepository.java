package com.ef.parser.repository;

import com.ef.parser.model.LogAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogAccessRepository extends JpaRepository<LogAccess, Long> {

    @Query(value = "SELECT log FROM LogAccess log WHERE log.dateAccess BETWEEN ?1 AND ?2 AND log.ip = ?3")
    List<LogAccess> findAllByIpAndDataAndDateAccess(LocalDateTime startDate, LocalDateTime endDate, String ip);

    @Query(value = "SELECT log.ip " +
                   "  FROM LogAccess  log " +
                   " WHERE log.dateAccess BETWEEN ?1 AND ?2 " +
                   " GROUP BY log.ip " +
                   " HAVING count(log.ip) > ?3 ")
    List<Object[]> findAllByDateAccessAndThresholdLimit(LocalDateTime startDate, LocalDateTime endDate, Long threshold);


}
