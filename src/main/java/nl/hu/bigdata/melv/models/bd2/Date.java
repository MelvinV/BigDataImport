package nl.hu.bigdata.melv.models.bd2;

import javax.persistence.*;

/**
 * Created by melvin on 8-3-2017.
 */

@Entity
@Table(name = "date")
public class Date {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private java.util.Date datetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.util.Date getDatetime() {
        return datetime;
    }

    public void setDatetime(java.util.Date datetime) {
        this.datetime = datetime;
    }
}
