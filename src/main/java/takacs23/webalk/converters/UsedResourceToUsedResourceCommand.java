package takacs23.webalk.converters;

import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import takacs23.webalk.commands.UsedResourceCommand;
import takacs23.webalk.models.UsedResource;

@Component
public class UsedResourceToUsedResourceCommand implements Converter<UsedResource, UsedResourceCommand> {

    private final CompanyOfCreationToCompanyOfCreationCommand companyOfCreationToCompanyOfCreationCommand;

    public UsedResourceToUsedResourceCommand(CompanyOfCreationToCompanyOfCreationCommand companyOfCreationToCompanyOfCreationCommand) {
        this.companyOfCreationToCompanyOfCreationCommand = companyOfCreationToCompanyOfCreationCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public UsedResourceCommand convert(UsedResource usedResource) {
        if(usedResource == null){
            return null;
        }

        final UsedResourceCommand usedResourceCommand = new UsedResourceCommand();
        usedResourceCommand.setId(usedResource.getId());

        if(usedResource.getSpirit() != null){
            usedResourceCommand.setSpiritId(usedResource.getSpirit().getId());
        }

        usedResourceCommand.setTypeOfResource(usedResource.getTypeOfResouce());
        usedResourceCommand.setAmountOfResource(usedResource.getAmountOfResource());
        usedResourceCommand.setCompanyOfCreation(companyOfCreationToCompanyOfCreationCommand
                .convert(usedResource.getCompanyOfCreation()));


        return usedResourceCommand;
    }
}
