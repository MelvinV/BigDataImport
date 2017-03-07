package nl.hu.bigdata.melv.models.bd1;

import javax.persistence.*;

/**
 * Created by melvin on 7-3-2017.
 */

@Entity
@Table(name = "measurement")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Quantity quantity;

    @JoinColumn(name = "phenomenomtype_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PhenomenomType phenomenomType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public PhenomenomType getPhenomenomType() {
        return phenomenomType;
    }

    public void setPhenomenomType(PhenomenomType phenomenomType) {
        this.phenomenomType = phenomenomType;
    }
}
