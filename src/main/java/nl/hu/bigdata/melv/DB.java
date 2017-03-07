package nl.hu.bigdata.melv;

import nl.hu.bigdata.melv.models.bd1.Observation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Created by melvin on 7-3-2017.
 */
public class DB {

    private SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();

    public void insert(Observation observation)
    {
        Session session = sessionFactory.openSession();

        Transaction t = session.beginTransaction();

        session.persist(observation);

        t.commit();
        session.close();
    }
}
