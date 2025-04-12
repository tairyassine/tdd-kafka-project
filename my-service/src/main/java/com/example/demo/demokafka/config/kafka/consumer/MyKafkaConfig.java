package com.example.demo.demokafka.config.kafka.consumer;


import com.example.demo.demokafka.config.kafka.CommonKafkaConfig;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;



@Configuration
@ConfigurationProperties(prefix = "app.kafka.my-consumer")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class MyKafkaConfig extends CommonKafkaConfig<String, GenericRecord> {
	

	@Value ("${app.kafka.my-consumer.topic.retry}")
	private String retryTopic;

	@Value ("${app.kafka.my-consumer.topic.error}")
	private String dltTopic;

	@Bean
	public ConsumerFactory<String, GenericRecord> consumerFactory(KafkaProperties kafkaProperties) {
		return consumerResource(kafkaProperties);
	}

	@Bean
	public ProducerFactory<String, GenericRecord> producerFactory(KafkaProperties kafkaProperties) {
		return producerResource(kafkaProperties);
	}

	@Bean
	public KafkaTemplate<String, GenericRecord> kafkaTemplate(ProducerFactory<String, GenericRecord> producerFactory) {
		return new KafkaTemplate<>(producerFactory);
	}


	/******************************
	 * TOPIC CONFIGURATION
	 ******************************/
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, GenericRecord> myListenerFactory(
			ConsumerFactory<String, GenericRecord> consumerFactory, KafkaTemplate<String, GenericRecord> kafkaTemplate,
			DeadLetterPublishingRecoverer movementDeadLetter) {

		return retryKafkaListenerContainerFactory(consumerFactory, retryTopic, kafkaTemplate, movementDeadLetter, fixedBackOffMain);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, GenericRecord> myRetryListenerFactory(
			ConsumerFactory<String, GenericRecord> consumerFactory,
			DeadLetterPublishingRecoverer myDeadLetter) {

		return retryKafkaListenerContainerFactory(consumerFactory, myDeadLetter, fixedBackOffRetry);
	}

	@Bean
	public DeadLetterPublishingRecoverer myDeadLetter(KafkaTemplate<String, GenericRecord> kafkaTemplate) {
		return new DeadLetterPublishingRecoverer(kafkaTemplate,
				(consumerRecord, exception) -> new TopicPartition((dltTopic), consumerRecord.partition()));
	}

}