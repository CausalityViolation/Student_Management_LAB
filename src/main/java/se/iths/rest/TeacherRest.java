package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.exceptionHandling.ResponseHandler;
import se.iths.service.SubjectService;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {


    TeacherService teacherService;
    SubjectService subjectService;
    ResponseHandler handler = new ResponseHandler();

    @Inject
    public TeacherRest(TeacherService teacherService, SubjectService subjectService) {
        this.teacherService = teacherService;
        this.subjectService = subjectService;
    }

    @GET
    public Response getTeachers() {
        List<Teacher> foundTeachers = teacherService.getAllTeachers();
        return Response.ok(foundTeachers).build();
    }

    @Path("{email}")
    @GET
    public Response getTeacherByEmail(@PathParam("email") String email) {
        Teacher found = teacherService.findTeacherByMail(email);
        handler.ensureEntityExists(found, email);
        return Response.ok(found).build();
    }


    @POST
    public Response addNewTeacher(Teacher newTeacher) {
        handler.ensureTeacherHasValues(newTeacher);
        handler.ensureTeacherHasEmail(newTeacher);
        Teacher found = teacherService.findTeacherByMail(newTeacher.getEmail());
        handler.ensureEntityDoesntExist(found, newTeacher.getEmail());
        teacherService.createTeacher(newTeacher);
        return handler.operationResponse();
    }

    @Path("{email}")
    @DELETE
    public Response removeTeacher(@PathParam("email") String email) {
        Teacher foundTeacher = teacherService.findTeacherByMail(email);
        handler.ensureEntityExists(foundTeacher, email);

        for (Subject sub : foundTeacher.findSubjects()) {
            subjectService.removeTeacher(sub);
        }
        foundTeacher.removeSubjects();

        teacherService.deleteTeacher(email);
        return handler.operationResponse();
    }

    @Path("{subjectName}/{email}")
    @PATCH
    public Response addSubjectToTeacher(@PathParam("email") String email, @PathParam("subjectName") String subjectName) {
        Subject foundSubject = subjectService.getByName(subjectName);
        Teacher foundTeacher = teacherService.findTeacherByMail(email);
        handler.ensureEntityExists(foundTeacher, email);
        handler.ensureEntityExists(foundSubject, subjectName);
        handler.duplicateSubjectEliminator(foundTeacher.findSubjects(), subjectName, foundTeacher.getEmail());
        teacherService.addSubjectToTeacher(email, foundSubject);
        subjectService.addTeacher(foundTeacher, subjectName);
        return handler.operationResponse();
    }

    @Path("{email}")
    @PATCH
    public Response updateTeacherInfo(Teacher teacher, @PathParam("email") String email) {
        handler.ensureTeacherHasValues(teacher);
        Teacher foundTeacher = teacherService.findTeacherByMail(email);
        handler.ensureEntityExists(foundTeacher, email);
        teacher.setEmail(email);
        teacherService.updateTeacherInfo(teacher);
        return handler.operationResponse();
    }


}
