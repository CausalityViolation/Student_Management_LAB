package se.iths.service;

import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class TeacherService {

    @PersistenceContext
    EntityManager entityManager;

    public void createTeacher(Teacher teacher) {
        entityManager.persist(teacher);
    }

    public List<Teacher> getAllTeachers() {
        return entityManager.createQuery("SELECT t from Teacher t", Teacher.class).getResultList();
    }

    public Teacher findTeacherByMail(String email) {
        return entityManager.find(Teacher.class, email);
    }

    public void deleteTeacher(String email) {
        Teacher foundTeacher = entityManager.find(Teacher.class, email);
        entityManager.merge(foundTeacher);
        entityManager.remove(foundTeacher);
    }

    public void addSubjectToTeacher(String email, Subject subject) {
        Teacher foundTeacher = entityManager.find(Teacher.class, email);
        foundTeacher.addSubject(subject);
        entityManager.merge(foundTeacher);
    }

    public void updateTeacherInfo(Teacher teacher) {
        entityManager.merge(teacher);
    }
}
