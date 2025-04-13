package com.example.demo.demokafka.core.port.out.webclient;

import com.example.demo.demokafka.core.domain.model.MyModel;

public interface ExternalApiClient {

    String postToExternalApi(MyModel model);
}
