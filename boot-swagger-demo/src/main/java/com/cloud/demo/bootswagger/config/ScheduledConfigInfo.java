package com.cloud.demo.bootswagger.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@ConfigurationProperties(prefix = "data.collect")
public class ScheduledConfigInfo {

    private List<ScheduledConfig> scheduled = new ArrayList<>();

    public List<ScheduledConfig> getScheduled() {
        return scheduled;
    }

    public void setList(List<ScheduledConfig> scheduled) {
        this.scheduled = scheduled;
    }
}
