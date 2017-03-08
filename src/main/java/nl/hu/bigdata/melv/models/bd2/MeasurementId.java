package nl.hu.bigdata.melv.models.bd2;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by melvin on 8-3-2017.
 */
@Embeddable
public class MeasurementId implements Serializable {

    private Person person;

    private nl.hu.bigdata.melv.models.bd2.Date datetime;

    private Phenomenon phenomenon;

    private Unit unit;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public nl.hu.bigdata.melv.models.bd2.Date getDatetime() {
        return datetime;
    }

    public void setDatetime(nl.hu.bigdata.melv.models.bd2.Date datetime) {
        this.datetime = datetime;
    }

    public Phenomenon getPhenomenon() {
        return phenomenon;
    }

    public void setPhenomenon(Phenomenon phenomenon) {
        this.phenomenon = phenomenon;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
