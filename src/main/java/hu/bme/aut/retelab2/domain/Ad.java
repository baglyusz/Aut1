package hu.bme.aut.retelab2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Ad {

    @Id
    @GeneratedValue
    private Long id;

    private String address;

    private String description;

    private int price;

    private Date created;

    public Long getId() {return id; }

    public void setId(Long id) { this.id = id; }

}
