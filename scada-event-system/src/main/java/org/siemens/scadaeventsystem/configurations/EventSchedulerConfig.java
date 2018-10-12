package org.siemens.scadaeventsystem.configurations;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.siemens.scadaeventsystem.jobfactory.EventJobFactory;
import org.siemens.scadaeventsystem.jobs.EventJob;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import brave.sampler.Sampler;
import liquibase.integration.spring.SpringLiquibase;


@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
public class EventSchedulerConfig {
	
	@Bean
	public Sampler defaultSampler(){
		return Sampler.ALWAYS_SAMPLE;
	}
	
	@Bean
    public JobFactory jobFactory(ApplicationContext applicationContext,  SpringLiquibase springLiquibase)
    {
        EventJobFactory jobFactory = new EventJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }
	
	@Bean
    public Scheduler schedulerFactoryBean(DataSource dataSource, JobFactory jobFactory,
                                          @Qualifier("eventJobTrigger") Trigger eventJobTrigger) throws Exception {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(dataSource);
        factory.setJobFactory(jobFactory);
        factory.setQuartzProperties(quartzProperties());
        factory.afterPropertiesSet();
        
        Scheduler scheduler = factory.getScheduler();
        scheduler.setJobFactory(jobFactory);
        scheduler.scheduleJob((JobDetail) eventJobTrigger.getJobDataMap().get("jobDetail"),eventJobTrigger);

        scheduler.start();
        return scheduler;
    }
	
	@Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }
	
	@Bean
    public JobDetailFactoryBean eventJobDetail() {
        return createJobDetail(EventJob.class);
    }
	
	@Bean(name = "eventJobTrigger")
    public CronTriggerFactoryBean cronJobTrigger(@Qualifier("eventJobDetail") JobDetail jobDetail, @Value("${eventjob.frequency}") String frequency) {
        return createCronTrigger(jobDetail, frequency);
    }
	
	private static JobDetailFactoryBean createJobDetail(Class jobClass) {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(jobClass);
        // job has to be durable to be stored in DB:
        factoryBean.setDurability(true);
        return factoryBean;
    }
	
	// Use this method for creating cron triggers instead of simple triggers:
    private static CronTriggerFactoryBean createCronTrigger(JobDetail jobDetail, String cronExpression) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setCronExpression(cronExpression);
        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
        return factoryBean;
    }

}
