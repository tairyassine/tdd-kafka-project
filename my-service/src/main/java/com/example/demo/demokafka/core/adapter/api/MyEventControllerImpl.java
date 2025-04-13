package com.example.demo.demokafka.core.adapter.api;

import com.example.demo.demokafka.core.domain.model.MyModel;
import com.example.demo.demokafka.core.port.in.api.MyEventController;
import com.example.demo.demokafka.core.port.out.database.MyEventDataGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/model")
@RequiredArgsConstructor
public class MyEventControllerImpl implements MyEventController {

    private final MyEventDataGateway myEventDataGateway;

    @Override
    public ResponseEntity<Optional<MyModel>> getMyModel(Long id) {
        return ResponseEntity.ok(myEventDataGateway.retrieveEvent(id));
    }
}
