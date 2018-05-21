package takacs23.webalk.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class UsedResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeOfResouce;
    private BigDecimal amountOfResource;

    @OneToOne(fetch = FetchType.EAGER)
    private CompanyOfCreation companyOfCreation;

    @ManyToOne
    private Spirit spirit;

    public UsedResource() {
    }

    public UsedResource(String typeOfResouce, BigDecimal amountOfResource, CompanyOfCreation companyOfCreation) {
        this.typeOfResouce = typeOfResouce;
        this.amountOfResource = amountOfResource;
        this.companyOfCreation = companyOfCreation;
    }

    public UsedResource(String typeOfResouce, BigDecimal amountOfResource, CompanyOfCreation companyOfCreation, Spirit spirit) {
        this.typeOfResouce = typeOfResouce;
        this.amountOfResource = amountOfResource;
        this.companyOfCreation = companyOfCreation;
        this.spirit = spirit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeOfResouce() {
        return typeOfResouce;
    }

    public void setTypeOfResouce(String typeOfResouce) {
        this.typeOfResouce = typeOfResouce;
    }

    public BigDecimal getAmountOfResource() {
        return amountOfResource;
    }

    public void setAmountOfResource(BigDecimal amountOfResource) {
        this.amountOfResource = amountOfResource;
    }

    public Spirit getSpirit() {
        return spirit;
    }

    public void setSpirit(Spirit spirit) {
        this.spirit = spirit;
    }

    public CompanyOfCreation getCompanyOfCreation() {
        return companyOfCreation;
    }

    public void setCompanyOfCreation(CompanyOfCreation companyOfCreation) {
        this.companyOfCreation = companyOfCreation;
    }
}
