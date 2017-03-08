package nl.hu.bigdata.melv;

import nl.hu.bigdata.melv.models.bd1.Phenomenon;
import nl.hu.bigdata.melv.models.bd1.PhenomenonType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Created by melvin on 7-3-2017.
 */
public class Test {
    public static void main(String[] args) {
        Session session = new AnnotationConfiguration()
                .configure().buildSessionFactory().openSession();

        Transaction t = session.beginTransaction();

        Employee e1 = new Employee();
        //e1.setId(1001);
        e1.setFirstName("Melvin");
        e1.setLastName("de Valk");

        Employee e2 = new Employee();
        //e2.setId(1002);
        e2.setFirstName("Gabrielle");
        e2.setLastName("Nugteren");

        PhenomenonType phenomenonType = new PhenomenonType();
        phenomenonType.setName("Blood Pressure");

        Phenomenon phenomenon = new Phenomenon();
        phenomenon.setName("High Pressure");
        phenomenon.setPhenomenonType(phenomenonType);

        session.persist(phenomenon);

        session.persist(e1);
        session.persist(e2);

        t.commit();
        session.close();
        System.out.println("successfully saved!~");
    }
}