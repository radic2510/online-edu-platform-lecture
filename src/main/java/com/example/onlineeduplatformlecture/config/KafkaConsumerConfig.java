package com.example.onlineeduplatformlecture.config;

import com.example.onlineeduplatformlecture.model.Rating;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootStrapServers;
//
//    @Bean
//    public Map<String, Object> ratingConsumerConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "foo");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        return props;
//    }
//
//    @Bean
//    public ConsumerFactory<String, Rating> ratingSaveConsumer() {
//        return new DefaultKafkaConsumerFactory<>(ratingConsumerConfigs());
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, Rating> ratingSaveListener() {
//        ConcurrentKafkaListenerContainerFactory<String, Rating> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(ratingSaveConsumer());
//        return factory;
//    }


//    public ConsumerFactory<String, String> consumerFactory(String groupId){
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        return new DefaultKafkaConsumerFactory<>(props);
//    }
//
//    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(String groupId) {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory(groupId));
//        return factory;
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> fooKafkaListenerContainerFactory() {
//        return kafkaListenerContainerFactory("foo");
//    }
//
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String,String> filterKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory = kafkaListenerContainerFactory("filter");
//        factory.setRecordFilterStrategy(record -> record.value().contains("world"));
//        return factory;
//    }

    @Bean
    public ConsumerFactory<String, Rating> greetingConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "rating");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Rating.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Rating> ratingKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Rating> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(greetingConsumerFactory());
        return factory;
    }

}
