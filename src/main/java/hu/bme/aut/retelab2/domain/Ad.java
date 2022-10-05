package hu.bme.aut.retelab2.domain;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
public class Ad {

    @Id
    @GeneratedValue
    private Long id;

    private String address;

    private String description;

    private int price;

    private Date created;

    private String token;

    private LocalDateTime expirationDate;

    @ElementCollection
    private List<String> tags = new ArrayList<>();

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

    public String getToken(){ return token; }

    public void setToken(String secretToken){ this.token = secretToken; }

    public List<String> getTags() { return tags; }

    public void setTags(List<String> tags) { this.tags = tags; }

    public static String SecretGenerator(){
        char[] CHARS =
                "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        Random RND = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < 6; i++)
            sb.append(CHARS[RND.nextInt(CHARS.length)]);
        return sb.toString();
    }

    public LocalDateTime getExpirationDate() { return expirationDate; }

    public void setExpirationDate(LocalDateTime expirationDate) { this.expirationDate = expirationDate; }
}
