package com.example.demo.demokafka.config.kafka.producer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class SpecificRecordSenderImpl implements IRecordSender<String, GenericRecord> {

	private KafkaTemplate<String, GenericRecord> KafkaTemplate;

	@Override
	public void sendToKafka(ProducerRecord<String, GenericRecord> producerRecord) {
		sendRecord(KafkaTemplate, producerRecord);
	}
}
