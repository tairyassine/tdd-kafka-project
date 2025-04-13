package com.example.demo.demokafka.core.port.out.messaging;

import com.example.demo.demokafka.core.domain.model.MyModel;

public interface MyEventProducer {
    void publishOutputEvent(MyModel event);
}
