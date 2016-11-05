package com.avvero.rss_bot;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.jms.JmsConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by fxdev-belyaev-ay on 03.11.16.
 */
@ComponentScan
@EnableAsync
@EnableScheduling
@EnableAutoConfiguration
public class App {

    @Value("${rss_collector.jms_broker_url}")
    public String jmsBrokerUrl;
    @Value("${rss_collector.jms_broker_user_name}")
    public String jmsBrokerUserName;
    @Value("${rss_collector.jms_broker_password}")
    public String jmsBrokerPassword;

    public static void main(String args[]) throws Throwable {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public ActiveMQConnectionFactory pooledConnectionFactory() {
        return new ActiveMQConnectionFactory(jmsBrokerUserName, jmsBrokerPassword, jmsBrokerUrl);
    }

    @Bean
    public JmsConfiguration jmsConfig() {
        JmsConfiguration configuration = new JmsConfiguration(pooledConnectionFactory());
        configuration.setConcurrentConsumers(10);
        return configuration;
    }

    @Bean
    public ActiveMQComponent activemq() {
        ActiveMQComponent activeMQComponent = new ActiveMQComponent();
        activeMQComponent.setConfiguration(jmsConfig());
        return activeMQComponent;
    }

    @Bean
    public JacksonDataFormat jacksonDataFormat() {
        return new JacksonDataFormat();
    }
}
