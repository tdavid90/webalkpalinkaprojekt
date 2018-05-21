package takacs23.webalk.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Spirit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private Integer quantity;
    private Integer percentageOfAlcohol;

    @Enumerated(value = EnumType.STRING) //ORDINAL 1..2..3..
    private Quality quality;
    private String description;
    private String brewLocation;

    @ManyToMany
    @JoinTable(name = "spirit_qualityChecker",
            joinColumns = @JoinColumn(name = "spirit_id"),
            inverseJoinColumns = @JoinColumn(name = "qualityChecker_id"))
    private Set<QualityChecker> qualityCheckers = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "spirit")
    private Set<UsedResource> usedResources = new HashSet<>(); //avoid null pointer

    @OneToOne(cascade = CascadeType.ALL)
    private BrewProcess brewProcess;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPercentageOfAlcohol() {
        return percentageOfAlcohol;
    }

    public void setPercentageOfAlcohol(Integer percentageOfAlcohol) {
        this.percentageOfAlcohol = percentageOfAlcohol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrewLocation() {
        return brewLocation;
    }

    public void setBrewLocation(String brewLocation) {
        this.brewLocation = brewLocation;
    }

    public BrewProcess getBrewProcess() {
        return brewProcess;
    }

    public void setBrewProcess(BrewProcess brewProcess) {
        if(brewProcess != null) {
            this.brewProcess = brewProcess;
            brewProcess.setSpirit(this);
        }
    }

    public Spirit addUsedResource(UsedResource usedResource){
        usedResource.setSpirit(this);
        this.usedResources.add(usedResource);
        return this;
    }

    public Set<UsedResource> getUsedResources() {
        return usedResources;
    }

    public void setUsedResources(Set<UsedResource> usedResources) {
        this.usedResources = usedResources;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public Set<QualityChecker> getQualityCheckers() {
        return qualityCheckers;
    }

    public void setQualityCheckers(Set<QualityChecker> qualityCheckers) {
        this.qualityCheckers = qualityCheckers;
    }
}
