package com.example.userorder.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

    @Value("${spring.kafka.template.default-topic}") // factory.annotation.Value not lombok
    private String topicName;

    @Bean
    public NewTopic createTopic() {
        return TopicBuilder.name(topicName).partitions(1).replicas(1).build();
    }
}
