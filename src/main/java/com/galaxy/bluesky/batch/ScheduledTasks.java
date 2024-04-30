package com.galaxy.bluesky.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class ScheduledTasks {

    //This will run every 10 seconds
    public String task001(Map<String, Object> paramMap) {
        String status = "fail";
        log.info(paramMap.get("taskId").toString() + " : " + paramMap);
        status = "success";
        return status;
    }

    //This will run every 10 seconds
    public String task003(Map<String, Object> paramMap) {
        String status = "fail";
        log.info(paramMap.get("taskId").toString() + " : " + paramMap);
        status = "success";
        return status;
    }
}
