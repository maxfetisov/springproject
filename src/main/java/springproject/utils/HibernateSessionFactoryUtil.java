package springproject.utils;


import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import springproject.models.Good;
import springproject.models.Person;
import springproject.models.Purchase;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;
    private HibernateSessionFactoryUtil(){}

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Good.class);
            configuration.addAnnotatedClass(Person.class);
            configuration.addAnnotatedClass(Purchase.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
        return sessionFactory;
    }
}
