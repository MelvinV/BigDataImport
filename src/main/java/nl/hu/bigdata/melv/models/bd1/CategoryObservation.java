package nl.hu.bigdata.melv.models.bd1;

import javax.persistence.*;

/**
 * Created by melvin on 7-3-2017.
 */

@Entity
@Table(name = "categoryobservation")
public class CategoryObservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Phenomenom phenomenom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Phenomenom getPhenomenom() {
        return phenomenom;
    }

    public void setPhenomenom(Phenomenom phenomenom) {
        this.phenomenom = phenomenom;
    }
}
