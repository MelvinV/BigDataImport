package nl.hu.bigdata.melv.models.bd1;

import javax.persistence.*;

/**
 * Created by melvin on 7-3-2017.
 */

@Entity
@Table(name = "phenomenom")
public class Phenomenom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 50)
    private String name;

    @JoinColumn(name = "phenomenomtype_id")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PhenomenomType phenomenomType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PhenomenomType getPhenomenomType() {
        return phenomenomType;
    }

    public void setPhenomenomType(PhenomenomType phenomenomType) {
        this.phenomenomType = phenomenomType;
    }
}
