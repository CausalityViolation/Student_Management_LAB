package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager entityManager;

    public void create(Student student) {
        entityManager.persist(student);
    }

    public List<Student> getAll() {
        return entityManager.createQuery("SELECT s from Student s", Student.class).getResultList();
    }

    public List<Student> findByName(String lastName) {
        return entityManager.createQuery("SELECT s FROM Student s WHERE s.lastName = :lastName", Student.class).setParameter("lastName", lastName).getResultList();
    }

    public Student findByEmail(String email) {
        return entityManager.find(Student.class, email);
    }

    public void delete(String email) {
        Student foundStudent = entityManager.find(Student.class, email);
        entityManager.remove(foundStudent);
    }

    public void addSubject(Subject subject, String email) {
        Student foundStudent = entityManager.find(Student.class, email);
        foundStudent.addSubject(subject);
        entityManager.merge(foundStudent);
    }

    public void update(Student student) {
        entityManager.merge(student);
    }
}
