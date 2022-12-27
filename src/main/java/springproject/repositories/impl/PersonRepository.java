package springproject.repositories.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import springproject.models.Good;
import springproject.models.Person;
import springproject.repositories.EntityRepository;
import springproject.utils.HibernateSessionFactoryUtil;

import java.util.Collection;
import java.util.Optional;

@Repository
public class PersonRepository implements EntityRepository<Person, String> {
    @Override
    public void create(Person entity) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
            session.save(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public Collection<Person> getAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            return session.createQuery("from Person").getResultList();
        }
    }

    @Override
    public Optional<Person> getById(String id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            return Optional.of(session.get(Person.class, id));
        }
    }

    @Override
    public void update(Person entity) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
            Person person = session.get(Person.class, entity.getLogin());
            person.setName(entity.getName());
            person.setPassword(entity.getPassword());
            person.setEmail(entity.getEmail());
            session.update(person);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(String id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
            session.remove(session.get(Person.class, id));
            session.getTransaction().commit();
        }
    }
}
