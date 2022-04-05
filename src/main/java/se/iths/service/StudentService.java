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

    public List<Student> findByID(Long id) {
        return entityManager.createQuery("SELECT s FROM Student s WHERE s.id = :id", Student.class).setParameter("id", id).getResultList();
    }

    public Student findByEmail(String email) {
        return entityManager.createQuery("SELECT s FROM Student s WHERE s.email = :email", Student.class).setParameter("email", email).getSingleResult();
    }


    public void updateFirstName(String firstName, Long id) {
        Student foundStudent = entityManager.find(Student.class, id);
        foundStudent.setFirstName(firstName);
    }

    public void updateLastName(String lastName, Long id) {
        Student foundStudent = entityManager.find(Student.class, id);
        foundStudent.setLastName(lastName);
    }

    public void updatePhone(String phone, Long id) {
        Student foundStudent = entityManager.find(Student.class, id);
        foundStudent.setPhoneNumber(phone);
    }

    public void updateEmail(String email, Long id) {
        Student foundStudent = entityManager.find(Student.class, id);
        foundStudent.setEmail(email);
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
}
