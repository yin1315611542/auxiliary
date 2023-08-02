package cn.goldencis.auxiliary.infrastructure.quartz;

import org.apache.commons.collections.MapUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class QuartzManager {

    /**
     * 任务调度管理单例对象
     */
    private static QuartzManager manager = new QuartzManager();

    /**
     * 任务调度
     */
    private Scheduler scheduler;

    private QuartzManager() {
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
        } catch (SchedulerException e) {
            throw new RuntimeException("构造调度任务异常...", e);
        }
    }

    /**
     * 获取实例方法
     *
     * @return QuartzManager;
     */
    public static QuartzManager getInstance() {
        return manager;
    }

    /**
     * 添加任务
     *
     * @param name           任务名
     * @param group          任务组
     * @param clazz          任务class
     * @param cronExpression 克隆表达式
     * @param jobDatalMap    任务附加参数
     * @throws SchedulerException 添加任务失败抛出该异常
     */
    public void addJob(String name, String group, Class<? extends Job> clazz, String cronExpression,
                       Map<String, ?> jobDatalMap) throws SchedulerException {
        JobDetail job = JobBuilder.newJob(clazz).withIdentity(name, group).storeDurably().build();
        if (MapUtils.isNotEmpty(jobDatalMap))
            job.getJobDataMap().putAll(jobDatalMap);

        Trigger trg = TriggerBuilder.newTrigger().withIdentity(name, group)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();

        scheduler.scheduleJob(job, trg);
    }

    /**
     * 更新jobDataMap
     *
     * @throws SchedulerException
     */
    public void updateJobDataMap(String name, String group, Map<String, ?> jobDatalMap) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        jobDetail.getJobDataMap().putAll(jobDatalMap);
        scheduler.addJob(jobDetail, true);
    }

    /**
     * 删除任务
     *
     * @param name  任务名
     * @param group 任务组
     * @throws SchedulerException 添加任务失败抛出该异常
     */
    public void removeJob(String name, String group) throws SchedulerException {
        TriggerKey tk = TriggerKey.triggerKey(name, group);
        scheduler.pauseTrigger(tk);
        scheduler.unscheduleJob(tk);
        JobKey jobKey = JobKey.jobKey(name, group);
        scheduler.deleteJob(jobKey);
    }

    /**
     * 暂停任务
     *
     * @param name  任务名
     * @param group 任务组
     * @throws SchedulerException 添加任务失败抛出该异常
     */
    public void pauseJob(String name, String group) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(name, group);
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复任务
     *
     * @param name  任务名
     * @param group 任务组
     * @throws SchedulerException 添加任务失败抛出该异常
     */
    public void resumeJob(String name, String group) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(name, group);
        scheduler.resumeJob(jobKey);
    }

    /**
     * 修改任务触发时间
     *
     * @param name  任务名
     * @param group 任务组
     * @throws SchedulerException 添加任务失败抛出该异常
     */
    public void modifyTime(String name, String group, String cronExpression) throws SchedulerException {
        TriggerKey tk = TriggerKey.triggerKey(name, group);
        Trigger trg = TriggerBuilder.newTrigger().withIdentity(name, group)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
        scheduler.rescheduleJob(tk, trg);
    }

    /**
     * 启动任务
     */
    public void start() throws SchedulerException {
        if (!scheduler.isStarted())
            scheduler.start();
    }

    /**
     * 停止任务
     */
    public void shutdown() throws SchedulerException {
        if (!scheduler.isShutdown())
            scheduler.shutdown();
    }

    /**
     * 获取任务上下文
     * 各任务数据共享
     *
     * @return SchedulerContext
     * @throws SchedulerException 获取上下文失败抛出异常
     */
    public SchedulerContext getContext() throws SchedulerException {
        return scheduler.getContext();
    }
}