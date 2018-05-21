package takacs23.webalk.commands;

import takacs23.webalk.models.Quality;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

public class SpiritCommand {

    private Long id;

    @NotBlank
    @Size(min= 5, max = 200)
    private String type;

    @Min(30)
    @Max(70)
    @NotNull
    private Integer percentageOfAlcohol;

    @Max(500)
    @NotNull
    private Integer quantity;
    private Quality quality;

    @NotBlank
    @Size(min = 5, max=200)
    private String description;

    @NotBlank
    @Size(min=2, max=100)
    private String brewLocation;
    private BrewProcessCommand brewProcess;
    private Set<UsedResourceCommand> usedResource = new HashSet<>();
    private Set<QualityCheckerCommand> qualityChecker = new HashSet<>();

    public SpiritCommand() {
    }

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

    public Integer getPercentageOfAlcohol() {
        return percentageOfAlcohol;
    }

    public void setPercentageOfAlcohol(Integer percentageOfAlcohol) {
        this.percentageOfAlcohol = percentageOfAlcohol;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
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

    public BrewProcessCommand getBrewProcess() {
        return brewProcess;
    }

    public void setBrewProcess(BrewProcessCommand brewProcess) {
        this.brewProcess = brewProcess;
    }

    public Set<UsedResourceCommand> getUsedResource() {
        return usedResource;
    }

    public void setUsedResource(Set<UsedResourceCommand> usedResource) {
        this.usedResource = usedResource;
    }

    public Set<QualityCheckerCommand> getQualityChecker() {
        return qualityChecker;
    }

    public void setQualityChecker(Set<QualityCheckerCommand> qualityChecker) {
        this.qualityChecker = qualityChecker;
    }
}
