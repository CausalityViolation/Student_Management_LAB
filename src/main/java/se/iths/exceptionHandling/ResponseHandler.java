package se.iths.exceptionHandling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import se.iths.entity.*;

public class ResponseHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    public void ensureEntityExists(Object entity, String entityName) {
        if (entity == null) {
            final String inputJson = "{\"Status:\":\"404 NOT FOUND\",\"Entity\":\"" + entityName + "\"}";

            try {
                throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                        .entity(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readTree(inputJson)))
                        .type(MediaType.APPLICATION_JSON).build());

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    public void ensureEntityDoesntExist(Object entity, String tag) {
        if (entity != null) {
            final String inputJson = "{\"Status:\":\"406 NOT ACCEPTABLE\",\"Entity\":\"" + tag + "\", \"Info:\":\"Already in database\"}";

            try {
                throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                        .entity(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readTree(inputJson)))
                        .type(MediaType.APPLICATION_JSON).build());

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    public Response operationResponse() {
        final String successfulResponse = "{\"Status\":\"OK\"}";

        try {
            return Response.ok(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readTree(successfulResponse))).build();

        } catch (Exception e) {
            return Response.status(404).build();
        }
    }

    public void ensureSubjectHasValues(Subject subject) {
        if (subject.getSubjectName() == null) {
            final String inputJson = "{\"Status:\":\"406 NOT ACCEPTABLE\",\"Entity\":\"UNKNOWN SUBJECT\", \"Info:\":\"subject Name required\"}";

            try {
                throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                        .entity(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readTree(inputJson)))
                        .type(MediaType.APPLICATION_JSON).build());

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    public void ensureTeacherHasValues(Teacher teacher) {

        if (teacher.getFirstName() == null || teacher.getLastName() == null || teacher.getEmail() == null) {
            final String inputJson = "{\"Status:\":\"406 NOT ACCEPTABLE\",\"Entity\":\"UNKNOWN TEACHER\", \"Info:\":\"FirstName, LastName and Email are required\"}";

            try {
                throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                        .entity(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readTree(inputJson)))
                        .type(MediaType.APPLICATION_JSON).build());

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    public void ensureStudentHasValues(Student student) {
        if (student.getEmail() == null || student.getLastName() == null || student.getFirstName() == null) {
            final String inputJson = "{\"Status:\":\"406 NOT ACCEPTABLE\",\"Entity\":\"UNKNOWN STUDENT\", \"Info:\":\"FirstName, LastName and Email are required\"}";

            try {
                throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                        .entity(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readTree(inputJson)))
                        .type(MediaType.APPLICATION_JSON).build());

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}
