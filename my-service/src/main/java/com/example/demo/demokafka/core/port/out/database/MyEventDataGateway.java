package com.example.demo.demokafka.core.port.out.database;

import com.example.demo.demokafka.core.domain.model.MyModel;

import java.util.Optional;

public interface MyEventDataGateway {

    void saveEvent(MyModel event);
    Optional<MyModel> retrieveEvent(Long id);

}

