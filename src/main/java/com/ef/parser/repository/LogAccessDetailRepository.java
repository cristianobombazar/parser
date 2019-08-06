package com.ef.parser.repository;

import com.ef.parser.model.LogAccessDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogAccessDetailRepository extends JpaRepository<LogAccessDetail, Long> {
}
