package com.supinfo.geekquote.resources;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import javax.ws.rs.core.MediaType;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
//@Provider
public class QuoteProvider extends JacksonJsonProvider {

    @Override
    public ObjectMapper locateMapper(Class<?> type, MediaType mediaType) {
        ObjectMapper mapper = super.locateMapper(type, mediaType);
        mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }
}
