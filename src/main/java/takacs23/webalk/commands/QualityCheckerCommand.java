package takacs23.webalk.commands;

import java.util.Set;

public class QualityCheckerCommand {
    private Long id;
    private String firstName;
    private String lastName;
    private String location;
    private Byte[] picture;
    private Set<SpiritCommand> spirit;

    public QualityCheckerCommand() {
    }

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

    public Set<SpiritCommand> getSpirit() {
        return spirit;
    }

    public void setSpirit(Set<SpiritCommand> spirit) {
        this.spirit = spirit;
    }
}
