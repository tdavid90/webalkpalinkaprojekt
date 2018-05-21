package takacs23.webalk.converters;

import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import takacs23.webalk.commands.SpiritCommand;
import takacs23.webalk.models.QualityChecker;
import takacs23.webalk.models.Spirit;
import takacs23.webalk.models.UsedResource;

@Component
public class SpiritToSpiritCommand implements Converter<Spirit, SpiritCommand> {

    private final BrewProcessToBrewProcessCommand brewProcessToBrewProcessCommand;
    private final QualityCheckerToQualityCheckerCommand qualityCheckerToQualityCheckerCommand;
    private final UsedResourceToUsedResourceCommand usedResourceToUsedResourceCommand;

    public SpiritToSpiritCommand(BrewProcessToBrewProcessCommand brewProcessToBrewProcessCommand,
                                 QualityCheckerToQualityCheckerCommand qualityCheckerToQualityCheckerCommand,
                                 UsedResourceToUsedResourceCommand usedResourceToUsedResourceCommand) {
        this.brewProcessToBrewProcessCommand = brewProcessToBrewProcessCommand;
        this.qualityCheckerToQualityCheckerCommand = qualityCheckerToQualityCheckerCommand;
        this.usedResourceToUsedResourceCommand = usedResourceToUsedResourceCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public SpiritCommand convert(Spirit spirit) {
        if(spirit == null){
            return null;
        }

        final SpiritCommand spiritCommand = new SpiritCommand();
        spiritCommand.setBrewLocation(spirit.getBrewLocation());
        spiritCommand.setDescription(spirit.getDescription());
        spiritCommand.setType(spirit.getType());
        spiritCommand.setId(spirit.getId());
        spiritCommand.setPercentageOfAlcohol(spirit.getPercentageOfAlcohol());
        spiritCommand.setQuality(spirit.getQuality());
        spiritCommand.setQuantity(spirit.getQuantity());
        spiritCommand.setBrewProcess(brewProcessToBrewProcessCommand.convert(spirit.getBrewProcess()));

        if (spirit.getUsedResources() != null && spirit.getUsedResources().size() > 0){
            spirit.getUsedResources().forEach(usedResource -> spiritCommand.getUsedResource()
                    .add(usedResourceToUsedResourceCommand.convert(usedResource)));
        }

        if (spirit.getQualityCheckers() != null && spirit.getQualityCheckers().size() > 0){
            spirit.getQualityCheckers().forEach((QualityChecker qualityChecker) -> spiritCommand.getQualityChecker()
                    .add(qualityCheckerToQualityCheckerCommand.convert(qualityChecker)));
        }

        return spiritCommand;
    }
}
