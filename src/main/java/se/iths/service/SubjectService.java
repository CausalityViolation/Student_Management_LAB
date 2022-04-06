package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class SubjectService {
    @PersistenceContext
    EntityManager manager;

    public void create(Subject subject) {
        manager.persist(subject);
    }

    public void update(Subject subject) {
        manager.merge(subject);
    }

    public void delete(Subject subject) {
        Subject found = manager.find(Subject.class, subject.getSubjectName());
        manager.remove(found);
    }

    public Subject getByName(String name) {
        return manager.find(Subject.class, name);
    }

    public List<Subject> getAll() {
        return manager.createQuery("SELECT x FROM Subject x ORDER BY x.subjectName", Subject.class).getResultList();
    }

    public void addStudent(Student student, String name) {
        Subject found = manager.find(Subject.class, name);
        found.addStudent(student);
        manager.merge(found);
    }

    public void addTeacher(Teacher teacher, String name) {
        Subject found = manager.find(Subject.class, name);
        found.setTeacher(teacher);
        manager.merge(found);
    }

    public void removeTeacher(Subject subject) {
        subject.setTeacher(null);
        manager.merge(subject);
    }

    public void removeStudent(Subject sub, Student toRemove) {
        sub.removeStudent(toRemove);
        manager.merge(sub);
    }
}
