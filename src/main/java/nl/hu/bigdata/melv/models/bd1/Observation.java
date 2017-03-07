package nl.hu.bigdata.melv.models.bd1;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by melvin on 7-3-2017.
 */

@Entity
@Table(name = "observation")
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date datetime;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Person person;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Measurement measurement;

    @JoinColumn(name = "categoryobservation_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private  CategoryObservation categoryObservation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public CategoryObservation getCategoryObservation() {
        return categoryObservation;
    }

    public void setCategoryObservation(CategoryObservation categoryObservation) {
        this.categoryObservation = categoryObservation;
    }
}
