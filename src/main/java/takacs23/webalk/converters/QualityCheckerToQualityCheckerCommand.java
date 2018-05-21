package takacs23.webalk.converters;

import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import takacs23.webalk.commands.QualityCheckerCommand;
import takacs23.webalk.models.QualityChecker;

@Component
public class QualityCheckerToQualityCheckerCommand implements Converter<QualityChecker, QualityCheckerCommand> {

   /* private SpiritToSpiritCommand spiritToSpiritCommand;

    public QualityCheckerToQualityCheckerCommand(SpiritToSpiritCommand spiritToSpiritCommand) {
        this.spiritToSpiritCommand = spiritToSpiritCommand;
    }

    public QualityCheckerToQualityCheckerCommand() {
    }*/

    @Synchronized
    @Nullable
    @Override
    public QualityCheckerCommand convert(QualityChecker qualityChecker) {
        if(qualityChecker == null){
            return null;
        }

        final QualityCheckerCommand qualityCheckerCommand = new QualityCheckerCommand();
        qualityCheckerCommand.setId(qualityChecker.getId());
        qualityCheckerCommand.setFirstName(qualityChecker.getFirstName());
        qualityCheckerCommand.setLastName(qualityChecker.getLastName());
        qualityCheckerCommand.setLocation(qualityChecker.getLocation());
        qualityCheckerCommand.setPicture(qualityChecker.getPicture());

        /*if(qualityChecker.getSpirits() != null && qualityChecker.getSpirits().size() > 0){
            qualityChecker.getSpirits().forEach(spirit -> qualityCheckerCommand.getSpirit()
                    .add(spiritToSpiritCommand.convert(spirit)));
        }*/

        return qualityCheckerCommand;
    }
}
