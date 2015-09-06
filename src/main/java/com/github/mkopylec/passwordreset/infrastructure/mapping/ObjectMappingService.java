package com.github.mkopylec.passwordreset.infrastructure.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Service
class ObjectMappingService {

    private final ModelMapper mapper = new ModelMapper();

    public ObjectMappingService() {
        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(PRIVATE);
    }

    //TODO Dodac potrzebne metody mapujace
}
