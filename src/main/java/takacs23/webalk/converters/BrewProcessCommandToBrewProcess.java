package takacs23.webalk.converters;

import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import takacs23.webalk.commands.BrewProcessCommand;
import takacs23.webalk.models.BrewProcess;

@Component
public class BrewProcessCommandToBrewProcess implements Converter<BrewProcessCommand, BrewProcess> {

   /* private final SpiritCommandToSpirits spiritCommandToSpirits;

    public BrewProcessCommandToBrewProcess(SpiritCommandToSpirits spiritCommandToSpirits) {
        this.spiritCommandToSpirits = spiritCommandToSpirits;
    } */

    @Synchronized
    @Nullable
    @Override
    public BrewProcess convert(BrewProcessCommand bc) {
        if(bc == null){
            return null;
        }

        final BrewProcess brewProcess = new BrewProcess();
        brewProcess.setId(bc.getId());
        brewProcess.setSpiritBrewProcess(bc.getSpiritBrewProcess());
       // brewProcess.setSpirit(spiritCommandToSpirits.convert(bc.getSpirit()));

        return brewProcess;
    }
}
