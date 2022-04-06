package se.iths.exceptionHandling;

import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.ws.rs.core.Response;
import java.util.List;

public class ResponseHandler {

    private final ObjectMapper mapper = new ObjectMapper();
    CustomExceptionBecauseReasons custom = new CustomExceptionBecauseReasons();

    public void ensureEntityExists(Object entity, String entityName) {
        if (entity == null) {
            final String inputJson = "{\"Status:\":\"404 NOT FOUND\",\"Entity\":\"" + entityName + "\"}";

            custom.throwException(inputJson, Response.Status.NOT_FOUND);
        }
    }

    public void ensureEntityDoesntExist(Object entity, String tag) {
        if (entity != null) {
            final String inputJson = "{\"Status:\":\"406 NOT ACCEPTABLE\",\"Entity\":\"" + tag + "\", \"Info:\":\"Already in database\"}";

            custom.throwException(inputJson, Response.Status.NOT_ACCEPTABLE);
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

    public void ensureTeacherHasValues(Teacher teacher) {

        if (teacher.getFirstName() == null || teacher.getLastName() == null) {
            final String inputJson = "{\"Status:\":\"406 NOT ACCEPTABLE\",\"Entity\":\"UNKNOWN TEACHER\", \"Info:\":\"firstName  and lastName are required\"}";

            custom.throwException(inputJson, Response.Status.NOT_ACCEPTABLE);
        }
    }

    public void ensureStudentHasValues(Student student) {
        if (student.getLastName() == null || student.getFirstName() == null) {
            final String inputJson = "{\"Status:\":\"406 NOT ACCEPTABLE\",\"Entity\":\"UNKNOWN STUDENT\", \"Info:\":\"firstName and lastName are required\"}";

            custom.throwException(inputJson, Response.Status.NOT_ACCEPTABLE);
        }
    }

    public void ensureStudentHasEmail(Student student) {
        if (student.getEmail() == null) {
            final String inputJson = "{\"Status:\":\"406 NOT ACCEPTABLE\",\"Entity\":\"UNKNOWN STUDENT\", \"Info:\":\"Email is required\"}";

            custom.throwException(inputJson, Response.Status.NOT_ACCEPTABLE);
        }
    }

    public void ensureTeacherHasEmail(Teacher teacher) {
        if (teacher.getEmail() == null) {
            final String inputJson = "{\"Status:\":\"406 NOT ACCEPTABLE\",\"Entity\":\"UNKNOWN TEACHER\", \"Info:\":\"Email is required\"}";

            custom.throwException(inputJson, Response.Status.NOT_ACCEPTABLE);
        }
    }

    public void duplicateSubjectEliminator(List<Subject> subjects, String subjectName, String entityName) {
        for (Subject sub : subjects) {
            if (sub.getSubjectName().equalsIgnoreCase(subjectName)) {
                final String inputJson = "{\"Status:\":\"406 NOT ACCEPTABLE\",\"Entity\":\"" + entityName + "\", \"Info:\":\"Duplicate entry found\"}";

                custom.throwException(inputJson, Response.Status.NOT_ACCEPTABLE);
            }
        }
    }


}

