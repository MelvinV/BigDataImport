package nl.hu.bigdata.melv.models.bd2;

import javax.persistence.*;

/**
 * Created by melvin on 7-3-2017.
 */

@Entity
@Table(name = "phenomenon")
public class Phenomenon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 50)
    private String name, type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
