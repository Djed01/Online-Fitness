package org.unibl.etf.onlinefitness.services;

import org.springframework.stereotype.Service;
import org.unibl.etf.onlinefitness.models.entities.LogEntity;
import org.unibl.etf.onlinefitness.models.enumeration.LogType;
import org.unibl.etf.onlinefitness.repositories.LogRepository;

import java.util.Date;

@Service
public class LogService {

    private final LogRepository logRepository;

    public LogService(LogRepository logRepository){
        this.logRepository = logRepository;
    }

    public void log(LogType type, String log){
        LogEntity logEntity = new LogEntity();
        logEntity.setDate(new Date());
        logEntity.setType(type.toString());
        logEntity.setDescription(log);
        this.logRepository.save(logEntity);
    }
}
