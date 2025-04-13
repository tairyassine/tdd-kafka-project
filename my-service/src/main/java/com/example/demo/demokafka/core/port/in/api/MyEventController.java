package com.example.demo.demokafka.core.port.in.api;

import com.example.demo.demokafka.core.domain.model.MyModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


public interface MyEventController {

    @GetMapping("/{id}")
    ResponseEntity<Optional<MyModel>> getMyModel(@PathVariable Long id);
}
