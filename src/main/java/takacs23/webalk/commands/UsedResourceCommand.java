package takacs23.webalk.commands;


import java.math.BigDecimal;

public class UsedResourceCommand {
    private Long id;
    private String typeOfResource;
    private BigDecimal amountOfResource;
    private Long spiritId;
    private CompanyOfCreationCommand companyOfCreation;

    public UsedResourceCommand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeOfResource() {
        return typeOfResource;
    }

    public void setTypeOfResource(String typeOfResource) {
        this.typeOfResource = typeOfResource;
    }

    public BigDecimal getAmountOfResource() {
        return amountOfResource;
    }

    public void setAmountOfResource(BigDecimal amountOfResource) {
        this.amountOfResource = amountOfResource;
    }

    public CompanyOfCreationCommand getCompanyOfCreation() {
        return companyOfCreation;
    }

    public void setCompanyOfCreation(CompanyOfCreationCommand companyOfCreation) {
        this.companyOfCreation = companyOfCreation;
    }

    public Long getSpiritId() {
        return spiritId;
    }

    public void setSpiritId(Long spiritId) {
        this.spiritId = spiritId;
    }
}
