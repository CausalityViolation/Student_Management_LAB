package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.exceptionHandling.ResponseHandler;
import se.iths.service.StudentService;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    ResponseHandler handler = new ResponseHandler();
    SubjectService subjectService;
    StudentService studentService;

    @Inject
    public SubjectRest(SubjectService service, StudentService studentService) {
        this.subjectService = service;
        this.studentService = studentService;
    }


    @Path("{subjectName}")
    @POST
    public Response create(@PathParam("subjectName") String subjectName) {
        handler.ensureEntityDoesntExist(subjectService.getByName(subjectName), subjectName);
        Subject newSubject = new Subject(subjectName);
        subjectService.create(newSubject);
        return handler.operationResponse();
    }


    @PUT
    public Response update(Subject subject) {
        handler.ensureSubjectHasValues(subject);
        handler.ensureEntityExists(subject, subject.getSubjectName());
        subjectService.update(subject);
        return handler.operationResponse();
    }

    @Path("{subjectName}/{studentEmail}")
    @PATCH
    public Response addStudent(@PathParam("subjectName") String subjectName, @PathParam("studentEmail") String studentEmail) {
        Subject foundSubject = subjectService.getByName(subjectName);
        handler.ensureEntityExists(foundSubject, subjectName);
        Student foundStudent = studentService.findByEmail(studentEmail);
        handler.ensureEntityExists(foundStudent, studentEmail);
        subjectService.addStudent(foundStudent, subjectName);
        studentService.addSubject(foundSubject, studentEmail);
        return handler.operationResponse();
    }


    @DELETE
    @Path("{subjectName}")
    public Response delete(@PathParam("subjectName") String name) {
        Subject found = subjectService.getByName(name);
        handler.ensureEntityExists(found, name);
        subjectService.delete(found);
        return handler.operationResponse();
    }

    @Path("{subjectName}")
    @GET
    public Response getByName(@PathParam("subjectName") String subjectName) {
        Subject found = subjectService.getByName(subjectName);
        handler.ensureEntityExists(found, subjectName);
        return Response.ok(found).build();
    }

    @GET
    public Response getAll() {
        return Response.ok(subjectService.getAll()).build();
    }

}
