package com.dev.encurta_ai.repository;

import com.dev.encurta_ai.model.LinkLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkLogRepository extends JpaRepository<LinkLog, Long> {
}
