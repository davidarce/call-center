package com.co.almundo.callcenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.co.almundo.callcenter.util.Constants;

/**
 * Created by gkatzioura on 4/26/17.
 */
@Configuration
@EnableAsync
public class ThreadConfig {

    @Bean
    @Primary
    public TaskExecutor threadPoolTaskExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Constants.MAX_THREADS);
        executor.setMaxPoolSize(Constants.MAX_THREADS);
        executor.setThreadNamePrefix("default_task_executor_thread");
        executor.initialize();

        return executor;
    }

    @Bean(name = "specificTaskExecutor")
    public TaskExecutor specificTaskExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.initialize();
        return executor;
    }

}