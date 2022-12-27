package springproject.repositories.impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import springproject.models.Good;
import springproject.models.Person;
import springproject.repositories.EntityRepository;
import springproject.utils.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;
import java.util.Optional;

@Repository
public class GoodRepository implements EntityRepository<Good, Integer> {

    @Override
    public void create(Good entity) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
            session.save(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public Collection<Good> getAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            return session.createQuery("from Good").getResultList();
        }
    }

    @Override
    public Optional<Good> getById(Integer id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            return Optional.of(session.get(Good.class, id));
        }
    }

    @Override
    public void update(Good entity) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
            Good good = session.get(Good.class, entity.getId());
            good.setName(entity.getName());
            good.setPrice(entity.getPrice());
            good.setPerson(entity.getPerson());
            session.update(good);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Integer id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
            session.remove(session.get(Good.class, id));
            session.getTransaction().commit();
        }
    }
}
