package com.example.demo.demokafka.core.adapter.messaging;

import com.example.demo.demokafka.event.MyEvent;
import com.example.demo.demokafka.core.domain.model.MyModel;
import com.example.demo.demokafka.core.domain.service.MyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMyEventConsumer {


    private final MyService myService;


    @KafkaListener(
            topics = "${app.kafka.my-consumer.topic.retry}",
            clientIdPrefix = "${spring.kafka.consumer.client-id}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "myRetryListenerFactory",
            autoStartup = "${app.kafka.my-consumer.enabled}"
    )
    @KafkaListener(
            topics = "${app.kafka.my-consumer.topic.main}",
            clientIdPrefix = "${spring.kafka.consumer.client-id}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "myListenerFactory",
            autoStartup = "${app.kafka.my-consumer.enabled}"
    )
    public void consumePaymentEvents(ConsumerRecord<String, MyEvent> consumerRecord) {
        System.out.println("received event:" + consumerRecord);
        log.info("received event: {}", consumerRecord);
        var myEvent = consumerRecord.value();
        myService.handleReceivedEvent(MyModel.fromEvent(myEvent));
    }

}
