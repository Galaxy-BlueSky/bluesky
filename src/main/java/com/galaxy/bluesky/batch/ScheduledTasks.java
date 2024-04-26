package com.galaxy.bluesky.batch;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    // This will run every 5 seconds
    @Scheduled(fixedRate = 5000)
    public void runEvery5Seconds() {
        // Task to run
        System.out.println("Running scheduled task every 5 seconds");
    }

    // This will run every minute
    @Scheduled(cron = "0 * * * * *")
    public void runEveryMinute() {
        // Task to run
        System.out.println("Running scheduled task every minute");
    }

    //This will run every day at 12:00 AM
    // This will run every day at midnight
    @Scheduled(cron = "0 0 0 * * *")
    public void runEveryDay() {
        // Task to run
        System.out.println("Running scheduled task every day");
        // Your code here
    }
}
