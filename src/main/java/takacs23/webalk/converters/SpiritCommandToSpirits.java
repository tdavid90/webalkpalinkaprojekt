package takacs23.webalk.converters;

import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import takacs23.webalk.commands.SpiritCommand;
import takacs23.webalk.models.Spirit;

@Component
public class SpiritCommandToSpirits implements Converter<SpiritCommand, Spirit> {

    private final QualityCheckerCommandToQualityChecker qualityCheckerCommandToQualityChecker;
    private final UsedResourceCommandToUsedResource usedResourceCommandToUsedResource;
    private final BrewProcessCommandToBrewProcess brewProcessCommandToBrewProcess;

    public SpiritCommandToSpirits(QualityCheckerCommandToQualityChecker qualityCheckerCommandToQualityChecker,
                                  UsedResourceCommandToUsedResource usedResourceCommandToUsedResource,
                                  BrewProcessCommandToBrewProcess brewProcessCommandToBrewProcess) {
        this.qualityCheckerCommandToQualityChecker = qualityCheckerCommandToQualityChecker;
        this.usedResourceCommandToUsedResource = usedResourceCommandToUsedResource;
        this.brewProcessCommandToBrewProcess = brewProcessCommandToBrewProcess;
    }

    @Synchronized
    @Nullable
    @Override
    public Spirit convert(SpiritCommand spiritCommand) {
        if(spiritCommand == null){
            return null;
        }

        final Spirit spirit = new Spirit();
        spirit.setId(spiritCommand.getId());
        spirit.setBrewLocation(spiritCommand.getBrewLocation());
        spirit.setType(spiritCommand.getType());
        spirit.setDescription(spiritCommand.getDescription());
        spirit.setPercentageOfAlcohol(spiritCommand.getPercentageOfAlcohol());
        spirit.setQuantity(spiritCommand.getQuantity());
        spirit.setQuality(spiritCommand.getQuality());
        spirit.setBrewProcess(brewProcessCommandToBrewProcess.convert(spiritCommand.getBrewProcess()));

        if (spiritCommand.getQualityChecker() != null && spiritCommand.getQualityChecker().size() > 0){
            spiritCommand.getQualityChecker()
                    .forEach(qualityChecker -> spirit.getQualityCheckers()
                            .add(qualityCheckerCommandToQualityChecker.convert(qualityChecker)));
        }

        if (spiritCommand.getUsedResource() != null && spiritCommand.getUsedResource().size() > 0){
            spiritCommand.getUsedResource()
                    .forEach(usedResource -> spirit.getUsedResources()
                            .add(usedResourceCommandToUsedResource.convert(usedResource)));
        }

        return spirit;
    }
}
