package se.iths.exceptionHandling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class CustomExceptionBecauseReasons extends WebApplicationException {

    private final ObjectMapper mapper = new ObjectMapper();

    public void throwException(String inputJson, Response.Status status) {
        try {
            throw new WebApplicationException(Response.status(status)
                    .entity(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readTree(inputJson)))
                    .type(MediaType.APPLICATION_JSON).build());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
