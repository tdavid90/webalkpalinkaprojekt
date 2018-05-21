package takacs23.webalk.converters;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import takacs23.webalk.commands.BrewProcessCommand;
import takacs23.webalk.models.BrewProcess;

@Component
public class BrewProcessToBrewProcessCommand implements Converter<BrewProcess, BrewProcessCommand> {

    /* private SpiritToSpiritCommand spiritToSpiritCommand;

    public BrewProcessToBrewProcessCommand(SpiritToSpiritCommand spiritToSpiritCommand) {
        this.spiritToSpiritCommand = spiritToSpiritCommand;
    }

    public BrewProcessToBrewProcessCommand() {
    } */

    @Synchronized
    @Nullable
    @Override
    public BrewProcessCommand convert(BrewProcess brewProcess)
    {
        if(brewProcess == null){
            return null;
        }

        final BrewProcessCommand brewProcessCommand = new BrewProcessCommand();
        brewProcessCommand.setId(brewProcess.getId());
        brewProcessCommand.setSpiritBrewProcess(brewProcess.getSpiritBrewProcess());
        //brewProcessCommand.setSpirit(spiritToSpiritCommand.convert(brewProcess.getSpirit()));

        return brewProcessCommand;
    }
}
