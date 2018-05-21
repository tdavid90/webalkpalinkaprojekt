package takacs23.webalk.converters;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import takacs23.webalk.commands.CompanyOfCreationCommand;
import takacs23.webalk.models.CompanyOfCreation;

@Component
public class CompanyOfCreationToCompanyOfCreationCommand implements Converter<CompanyOfCreation, CompanyOfCreationCommand> {

    @Synchronized
    @Nullable
    @Override
    public CompanyOfCreationCommand convert(CompanyOfCreation companyOfCreation) {
        if(companyOfCreation == null){
            return null;
        }

        final CompanyOfCreationCommand companyOfCreationCommand = new CompanyOfCreationCommand();
        companyOfCreationCommand.setId(companyOfCreation.getId());
        companyOfCreationCommand.setCompanyName(companyOfCreation.getCompanyName());

        return companyOfCreationCommand;
    }
}
