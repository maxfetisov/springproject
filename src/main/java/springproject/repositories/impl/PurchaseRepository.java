package springproject.repositories.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import springproject.models.Good;
import springproject.models.Purchase;
import springproject.repositories.EntityRepository;
import springproject.utils.HibernateSessionFactoryUtil;

import java.util.Collection;
import java.util.Optional;

@Repository
public class PurchaseRepository implements EntityRepository<Purchase, Integer> {

    @Override
    public void create(Purchase entity) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
            session.save(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public Collection<Purchase> getAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            return session.createQuery("from Purchase").getResultList();
        }
    }

    @Override
    public Optional<Purchase> getById(Integer id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            return Optional.of(session.get(Purchase.class, id));
        }
    }

    @Override
    public void update(Purchase entity) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
            Purchase purchase = session.get(Purchase.class, entity.getNumber());
            purchase.setGood(entity.getGood());
            purchase.setPerson(entity.getPerson());
            session.update(purchase);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Integer id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            session.getTransaction().begin();
            session.remove(session.get(Purchase.class, id));
            session.getTransaction().commit();
        }
    }
}
