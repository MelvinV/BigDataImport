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
    private Phenomenon phenomenon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Phenomenon getPhenomenon() {
        return phenomenon;
    }

    public void setPhenomenon(Phenomenon phenomenon) {
        this.phenomenon = phenomenon;
    }
}
