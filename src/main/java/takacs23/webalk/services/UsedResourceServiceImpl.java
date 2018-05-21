package takacs23.webalk.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import takacs23.webalk.commands.UsedResourceCommand;
import takacs23.webalk.converters.UsedResourceCommandToUsedResource;
import takacs23.webalk.converters.UsedResourceToUsedResourceCommand;
import takacs23.webalk.models.Spirit;
import takacs23.webalk.models.UsedResource;
import takacs23.webalk.repositories.CompanyOfCreationRepository;
import takacs23.webalk.repositories.SpiritRepository;

import java.util.Optional;

@Service
public class UsedResourceServiceImpl implements UsedResourceService {

    private final UsedResourceToUsedResourceCommand usedResourceToUsedResourceCommand;
    private final UsedResourceCommandToUsedResource usedResourceCommandToUsedResource;
    private final SpiritRepository spiritRepository;
    private final CompanyOfCreationRepository companyOfCreationRepository;

    public UsedResourceServiceImpl(UsedResourceToUsedResourceCommand usedResourceToUsedResourceCommand,
                                   UsedResourceCommandToUsedResource usedResourceCommandToUsedResource,
                                   SpiritRepository spiritRepository,
                                   CompanyOfCreationRepository companyOfCreationRepository) {
        this.usedResourceToUsedResourceCommand = usedResourceToUsedResourceCommand;
        this.usedResourceCommandToUsedResource = usedResourceCommandToUsedResource;
        this.spiritRepository = spiritRepository;
        this.companyOfCreationRepository = companyOfCreationRepository;
    }

    @Override
    public UsedResourceCommand findBySpiritIdAndUsedResourceId(Long spiritId, Long usedResourceId) {

        Optional<Spirit> spiritOptional = spiritRepository.findById(spiritId);

        Spirit spirit = spiritOptional.get();

        Optional<UsedResourceCommand> usedResourceCommandOptional = spirit.getUsedResources().stream()
                .filter(usedResource -> usedResource.getId().equals(usedResourceId))
                .map(usedResource -> usedResourceToUsedResourceCommand.convert(usedResource)).findFirst();

        return usedResourceCommandOptional.get();
    }

    @Override
    @Transactional
    public UsedResourceCommand saveUsedResourceCommand(UsedResourceCommand command) {
        Optional<Spirit> spiritOptional = spiritRepository.findById(command.getSpiritId());

        if(!spiritOptional.isPresent()){
            System.out.println("Nem található a következő ital ID: " + command.getSpiritId());
            return new UsedResourceCommand();
        }
        else {
            Spirit spirit = spiritOptional.get();

            Optional<UsedResource> usedResourceOptional = spirit.getUsedResources().stream()
                    .filter(usedResource -> usedResource.getId().equals(command.getId())).findFirst();

            if(usedResourceOptional.isPresent()){
                UsedResource usedResourceFound = usedResourceOptional.get();
                usedResourceFound.setAmountOfResource(command.getAmountOfResource());
                usedResourceFound.setTypeOfResouce(command.getTypeOfResource());
                usedResourceFound.setCompanyOfCreation(companyOfCreationRepository
                        .findById(command.getCompanyOfCreation().getId())
                        .orElseThrow(() -> new RuntimeException("Company not found")));
            } else {
                UsedResource usedResource = usedResourceCommandToUsedResource.convert(command);
                usedResource.setSpirit(spirit);
                spirit.addUsedResource(usedResource);
            }

            Spirit savedSpirit = spiritRepository.save(spirit);

            Optional<UsedResource> savedUsedResourceOptional = savedSpirit.getUsedResources().stream()
                    .filter(usedResource -> usedResource.getId().equals(command.getId()))
                    .findFirst();

            if(!savedUsedResourceOptional.isPresent()){
                savedUsedResourceOptional = savedSpirit.getUsedResources().stream()
                        .filter(usedResource -> usedResource.getTypeOfResouce().equals(command.getTypeOfResource()))
                        .filter(usedResource -> usedResource.getAmountOfResource().equals(command.getAmountOfResource()))
                        .filter(usedResource -> usedResource.getCompanyOfCreation().getId().equals(command.getCompanyOfCreation().getId()))
                        .findFirst();
            }

            return usedResourceToUsedResourceCommand.convert(savedUsedResourceOptional.get());
        }


    }

    @Override
    public void deleteBySpiritIdAndUsedResourceId(Long spiritId, Long usedResourceId) {

        Optional<Spirit> spiritOptional = spiritRepository.findById(spiritId);

        if(spiritOptional.isPresent()){
            Spirit spirit = spiritOptional.get();

            Optional<UsedResource> usedResourceOptional = spirit.getUsedResources().stream()
                    .filter(usedResource -> usedResource.getId().equals(usedResourceId)).findFirst();

            if(usedResourceOptional.isPresent()){
                UsedResource usedResourceToDelete = usedResourceOptional.get();
                usedResourceToDelete.setSpirit(null);
                spirit.getUsedResources().remove(usedResourceOptional.get());
                spiritRepository.save(spirit);
            }
        } else {
            System.out.println("Nem található az ital ID: " + spiritId);
        }

    }
}
