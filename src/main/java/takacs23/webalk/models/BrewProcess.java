package takacs23.webalk.models;

import javax.persistence.*;

@Entity
public class BrewProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Spirit spirit;

    @Lob
    private String spiritBrewProcess;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Spirit getSpirit() {
        return spirit;
    }

    public void setSpirit(Spirit spirit) {
        this.spirit = spirit;
    }

    public String getSpiritBrewProcess() {
        return spiritBrewProcess;
    }

    public void setSpiritBrewProcess(String spiritBrewProcess) {
        this.spiritBrewProcess = spiritBrewProcess;
    }
}
