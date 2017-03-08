package nl.hu.bigdata.melv;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Created by melvin on 7-3-2017.
 */
public class DB {

    private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();

    public Object save(Object object)
    {
        Session session = sessionFactory.openSession();

        Transaction t = session.beginTransaction();

        //session.saveOrUpdate(object);
        session.save(object);

        t.commit();
        session.close();

        return object;
    }

    public void insert(Object object)
    {
        Session session = sessionFactory.openSession();

        Transaction t = session.beginTransaction();

        //session.saveOrUpdate(object);
        session.persist(object);

        t.commit();
        session.close();
    }
}
