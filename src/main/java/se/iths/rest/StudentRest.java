package se.iths.rest;

import se.iths.entity.Student;
import se.iths.exceptionHandling.ResponseHandler;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    ResponseHandler handler = new ResponseHandler();

    StudentService studentService;

    @Inject
    public StudentRest(StudentService studentService) {
        this.studentService = studentService;
    }

    @GET
    public Response getStudents() {

        List<Student> foundStudents = studentService.getAll();
        return Response.ok(foundStudents).build();
    }

    @Path("query")
    @GET
    public Response getAllStudentWithLastName(@QueryParam("lastName") String lastName) {

        List<Student> foundStudents = studentService.findByName(lastName);
        return Response.ok(foundStudents).build();
    }


    @POST
    public Response addNewStudent(Student student) {
        handler.ensureStudentHasValues(student);
        handler.ensureEntityDoesntExist(studentService.findByEmail(student.getEmail()), student.getEmail());
        studentService.create(student);

        return handler.operationResponse();
    }

    @Path("{mail}")
    @DELETE
    public Response deleteStudentByMail(@PathParam("mail") String mail) {

        handler.ensureEntityExists(studentService.findByEmail(mail), mail);
        studentService.delete(mail);
        return handler.operationResponse();
    }

    @Path("{email}")
    @PATCH
    public Response updateStudentInfo(Student student, @PathParam("email") String email) {
        handler.ensureStudentHasValues(student);
        Student foundStudent = studentService.findByEmail(email);
        handler.ensureEntityExists(foundStudent, email);
        student.setEmail(email);
        studentService.update(student);
        return handler.operationResponse();

    }
}

