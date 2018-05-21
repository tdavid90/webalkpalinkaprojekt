package takacs23.webalk.services;

import org.springframework.stereotype.Service;
import takacs23.webalk.commands.CompanyOfCreationCommand;
import takacs23.webalk.converters.CompanyOfCreationToCompanyOfCreationCommand;
import takacs23.webalk.repositories.CompanyOfCreationRepository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CompanyOfCreationServiceImpl implements CompanyOfCreationService {

    private final CompanyOfCreationRepository companyOfCreationRepository;
    private final CompanyOfCreationToCompanyOfCreationCommand companyOfCreationToCompanyOfCreationCommand;

    public CompanyOfCreationServiceImpl(CompanyOfCreationRepository companyOfCreationRepository, CompanyOfCreationToCompanyOfCreationCommand companyOfCreationToCompanyOfCreationCommand) {
        this.companyOfCreationRepository = companyOfCreationRepository;
        this.companyOfCreationToCompanyOfCreationCommand = companyOfCreationToCompanyOfCreationCommand;
    }

    @Override
    public Set<CompanyOfCreationCommand> listAllCompanyOfCreation() {
        return StreamSupport.stream(companyOfCreationRepository.findAll()
                .spliterator(), false)
                .map(companyOfCreationToCompanyOfCreationCommand::convert)
                .collect(Collectors.toSet());
    }
}
