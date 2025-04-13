package com.example.demo.demokafka.core.adapter.db;

import com.example.demo.demokafka.core.adapter.db.repository.MyRepository;
import com.example.demo.demokafka.core.domain.model.MyModel;
import com.example.demo.demokafka.core.port.out.database.MyEventDataGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.example.demo.demokafka.core.adapter.db.entity.MyEntity.fromModel;
import static com.example.demo.demokafka.core.adapter.db.entity.MyEntity.toModel;

@Component
@RequiredArgsConstructor
public class MyEventDataAdapter implements MyEventDataGateway {

    private final MyRepository myRepository;

    @Override
    public void saveEvent(MyModel event) {
        myRepository.save(fromModel(event));
    }

    @Override
    public Optional<MyModel> retrieveEvent(Long id) {
        return myRepository.findById(id).map(e -> toModel(e));
    }
}
