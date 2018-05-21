package takacs23.webalk.converters;

import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import takacs23.webalk.commands.UsedResourceCommand;
import takacs23.webalk.models.Spirit;
import takacs23.webalk.models.UsedResource;

@Component
public class UsedResourceCommandToUsedResource implements Converter<UsedResourceCommand, UsedResource> {

    private final CompanyOfCreationCommandToCompanyOfCreation companyOfCreationCommandToCompanyOfCreation;

    public UsedResourceCommandToUsedResource(CompanyOfCreationCommandToCompanyOfCreation companyOfCreationCommandToCompanyOfCreation) {
        this.companyOfCreationCommandToCompanyOfCreation = companyOfCreationCommandToCompanyOfCreation;
    }

    @Synchronized
    @Nullable
    @Override
    public UsedResource convert(UsedResourceCommand urc) {
        if(urc == null)
        {
            return null;
        }
        final UsedResource usedResource = new UsedResource();
        usedResource.setId(urc.getId());

        if(urc.getSpiritId() != null){
            Spirit spirit = new Spirit();
            spirit.setId(urc.getSpiritId());
            usedResource.setSpirit(spirit);
            spirit.addUsedResource(usedResource);
        }

        usedResource.setAmountOfResource(urc.getAmountOfResource());
        usedResource.setTypeOfResouce(urc.getTypeOfResource());
        usedResource.setCompanyOfCreation(companyOfCreationCommandToCompanyOfCreation.convert(urc.getCompanyOfCreation()));


        return usedResource;
    }
}
