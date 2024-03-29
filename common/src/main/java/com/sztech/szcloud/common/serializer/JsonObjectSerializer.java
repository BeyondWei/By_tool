package com.sztech.szcloud.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class JsonObjectSerializer extends JsonSerializer<String> {


    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Object o = mapper.readValue(s, Object.class);
        jsonGenerator.writeObject(o);
    }
}
