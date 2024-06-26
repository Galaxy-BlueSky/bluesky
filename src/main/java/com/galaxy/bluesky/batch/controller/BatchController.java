package com.galaxy.bluesky.batch.controller;

import com.galaxy.bluesky.batch.service.BatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@EnableScheduling
@RestController
public class BatchController {
    // Inject the ScheduledTasks bean
    @Autowired
    BatchService batchService;

    public List<Map<String, Object>> getTaskList(String cronTime) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 10; i++){
            Map<String, Object> taskMap = new HashMap<String, Object>();
            taskMap.put("taskId", "task00"+i);
            list.add(taskMap);
        }
        return list;
    }

    // This will run every 5 seconds
//    @Scheduled(cron = "0/10 * * * * ?")
    public void runBatchScheduled() {
        try {
            List<Map<String, Object>> list = getTaskList("cron_0/10_*_*_*_*");
            String status = "fail";
            for (Map<String, Object> map : list) {
                status = getString(map);
                log.info("SchedulingMng.runEvery5Seconds taskId : {}, status : {}", map.get("taskId").toString(), status);
            }
        } catch (Exception e) {
            log.error("Exception : ", e);
        }
    }

    @RequestMapping("/apiBatchScheduled")
    public String apiBatchScheduled(@RequestParam Map<String, Object> paramMap) {
        String status = "fail";
        String taskId = (String) paramMap.get("taskId");
        try {
            if (taskId != null) status = getString(paramMap);
        } catch (Exception e) {
            log.error("Exception : ", e);
        }
        log.info("SchedulingMng.runEvery5Seconds taskId : {}, status : {}", taskId, status);
        return status;
    }

    private String getString(Map<String, Object> paramMap) throws Exception {
        Class<?> aClass = Class.forName(batchService.getClass().getName());
        Method[] aMethods = aClass.getDeclaredMethods();
        String status = "fail";
        for (Method aMethod : aMethods) {
            if(aMethod.getName().equals(paramMap.get("taskId").toString())){
                status  = (String) aMethod.invoke(batchService, paramMap);
            }
        }
        return status;
    }
}



