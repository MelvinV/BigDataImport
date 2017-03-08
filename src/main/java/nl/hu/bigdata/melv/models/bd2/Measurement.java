package nl.hu.bigdata.melv.models.bd2;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by melvin on 7-3-2017.
 */

@Entity
@Table(name = "measurement")
public class Measurement implements Serializable {

    /*
    @Id @OneToOne
    private Person person;

    @Id @OneToOne
    private Date datetime;

    @Id @OneToOne
    private Phenomenon phenomenon;

    @Id @OneToOne
    private Unit unit;
    */

    @Id
    private Long person_id;

    @Id
    private Long datetime_id;

    @Id
    private Long phenomenon_id;

    @Id
    private Long unit_id;

    private float amount;

    public Long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
    }

    public Long getDatetime_id() {
        return datetime_id;
    }

    public void setDatetime_id(Long datetime_id) {
        this.datetime_id = datetime_id;
    }

    public Long getPhenomenon_id() {
        return phenomenon_id;
    }

    public void setPhenomenon_id(Long phenomenon_id) {
        this.phenomenon_id = phenomenon_id;
    }

    public Long getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(Long unit_id) {
        this.unit_id = unit_id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
