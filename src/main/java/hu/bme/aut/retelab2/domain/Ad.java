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

    public String getAddress(){ return address; }

    public void setAddress(String address){ this.address = address; }

    public String getDescription(){ return description; }

    public void setDescription(String description){ this.description = description; }

    public int getPrice() { return price; }

    public void setPrice(int price){ this.price = price; }

    public Date getCreatedAt() { return created; }

    public void setCreatedAt(Date createdAt){ this.created = createdAt; }
}
