package takacs23.webalk.commands;

public class BrewProcessCommand {
    private Long id;
    private SpiritCommand spirit;
    private String spiritBrewProcess;

    public BrewProcessCommand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SpiritCommand getSpirit() {
        return spirit;
    }

    public void setSpirit(SpiritCommand spirit) {
        this.spirit = spirit;
    }

    public String getSpiritBrewProcess() {
        return spiritBrewProcess;
    }

    public void setSpiritBrewProcess(String spiritBrewProcess) {
        this.spiritBrewProcess = spiritBrewProcess;
    }
}
