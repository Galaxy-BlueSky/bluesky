package com.galaxy.bluesky.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
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
public class SchedulingMng {
    // Inject the ScheduledTasks bean
    @Autowired
    ScheduledTasks scheduledTasks;

    public List<Map<String, Object>> getTaskId(String cronTime) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 10; i++){
            Map<String, Object> taskMap = new HashMap<String, Object>();
            taskMap.put("taskId", "task00"+i);
            list.add(taskMap);
        }
        return list;
    }

    // This will run every 5 seconds
    @Scheduled(cron = "0/10 * * * * ?")
    public void runEvery5Seconds() {
        try {
            List<Map<String, Object>> list = getTaskId("cron_0/10_*_*_*_*");
            String status = "fail";
            for (Map<String, Object> map : list) {
                status = getString(map);
                log.info("SchedulingMng.runEvery5Seconds taskId : {}, status : {}", map.get("taskId").toString(), status);
            }
        } catch (Exception e) {
            log.error("Exception : {}", e);
        }
    }

    @RequestMapping("/requestRunTasks")
    public String requestRunTasks(@RequestParam Map<String, Object> paramMap) {
        String status = "fail";
        String taskId = (String) paramMap.get("taskId");
        try {
            if (taskId != null) status = getString(paramMap);
        } catch (Exception e) {
            log.error("Exception : {}", e);
        }
        log.info("SchedulingMng.runEvery5Seconds taskId : {}, status : {}", taskId, status);
        return status;
    }

    private String getString(Map<String, Object> paramMap) throws Exception {
        Class<?> aClass = Class.forName(scheduledTasks.getClass().getName());
        Method[] aMethods = aClass.getDeclaredMethods();
        String status = "fail";
        for (Method aMethod : aMethods) {
            if(aMethod.getName().equals(paramMap.get("taskId").toString())){
                status  = (String) aMethod.invoke(scheduledTasks, paramMap);
            }
        }
        return status;
    }
}



