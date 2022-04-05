package se.iths.utility;

import se.iths.entity.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class DataGenerator {
    @PersistenceContext
    EntityManager manager;

    @PostConstruct
    public void addData() {
        Student a = new Student("Arne", "And", "a@a.com", "123");
        Student b = new Student("Örjan", "And", "b@a.com", "070448984");
        Student c = new Student("Björne", "Björn", "b@b.com", "073569414");
        Teacher d = new Teacher("Mox", "Degg", "a@b.com");
        Teacher e = new Teacher("Gooby", "Degg", "c@c.com");
        Teacher f = new Teacher("Dolan", "Dekk", "a@c.com");
        Subject g = new Subject("English");
        Subject h = new Subject("Math");
        Subject i = new Subject("Space Alien Monster Tech");

        manager.persist(a);
        manager.persist(b);
        manager.persist(c);
        manager.persist(d);
        manager.persist(e);
        manager.persist(f);
        manager.persist(g);
        manager.persist(h);
        manager.persist(i);
    }
}
