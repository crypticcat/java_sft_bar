package sft.bar.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import sft.bar.mantis.model.UserData;

import java.util.List;

public class DbHelper {

    private ApplicationManager app;
    private SessionFactory sessionFactory;

    public DbHelper(ApplicationManager app) {
        this.app = app;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public List<UserData> users() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> result = session.createQuery("from UserData where username != 'administrator'").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}