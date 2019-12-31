package app;

import app.demo.job.DemoJob;
import core.framework.module.Module;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

/**
 * @author kimi
 */
public class JobModule extends Module {
    @Override
    protected void initialize() {
        LocalTime localTime = LocalTime.now();
        DemoJob demoJob = bind(DemoJob.class);
        schedule().fixedRate("fixedRateJob", demoJob, Duration.ofSeconds(30));
        schedule().monthlyAt("monthlyAtJob", demoJob, 1, localTime);
        schedule().weeklyAt("weeklyAtJob", demoJob, DayOfWeek.TUESDAY, localTime);
        schedule().dailyAt("dailyAtJob", demoJob, localTime);
        schedule().hourlyAt("hourlyAtJob", demoJob, 30, 0);
        schedule().trigger("triggerJob", demoJob, previous -> previous.plusMinutes(10));
    }
}
