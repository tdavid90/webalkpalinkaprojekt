package takacs23.webalk.converters;

import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import takacs23.webalk.commands.CompanyOfCreationCommand;
import org.springframework.core.convert.converter.Converter;
import takacs23.webalk.models.CompanyOfCreation;

@Component
public class CompanyOfCreationCommandToCompanyOfCreation implements Converter<CompanyOfCreationCommand, CompanyOfCreation> {

    @Synchronized
    @Nullable
    @Override
    public CompanyOfCreation convert(CompanyOfCreationCommand coc) {
        if(coc == null) {
            return null;
        }

        final CompanyOfCreation companyOfCreation = new CompanyOfCreation();
        companyOfCreation.setId(coc.getId());
        companyOfCreation.setCompanyName(coc.getCompanyName());

        return companyOfCreation;
    }
}
