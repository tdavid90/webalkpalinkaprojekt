package takacs23.webalk.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class QualityChecker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String location;

    @Lob
    private Byte[] picture;

    @ManyToMany(mappedBy = "qualityCheckers")
    private Set<Spirit> spirits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Byte[] getPicture() {
        return picture;
    }

    public void setPicture(Byte[] picture) {
        this.picture = picture;
    }

    public Set<Spirit> getSpirits() {
        return spirits;
    }

    public void setSpirits(Set<Spirit> spirits) {
        this.spirits = spirits;
    }
}
