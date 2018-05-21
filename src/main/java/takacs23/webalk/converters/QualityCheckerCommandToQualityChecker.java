package takacs23.webalk.converters;

import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import takacs23.webalk.commands.QualityCheckerCommand;
import takacs23.webalk.models.QualityChecker;

@Component
public class QualityCheckerCommandToQualityChecker implements Converter<QualityCheckerCommand, QualityChecker> {

   /* private final SpiritCommandToSpirits spiritCommandToSpirits;

    public QualityCheckerCommandToQualityChecker(SpiritCommandToSpirits spiritCommandToSpirits) {
        this.spiritCommandToSpirits = spiritCommandToSpirits;
    } */

    @Synchronized
    @Nullable
    @Override
    public QualityChecker convert(QualityCheckerCommand qcc) {
        if(qcc == null){
            return null;
        }

        final QualityChecker qualityChecker = new QualityChecker();
        qualityChecker.setPicture(qcc.getPicture());
        qualityChecker.setId(qcc.getId());
        qualityChecker.setLocation(qcc.getLocation());
        qualityChecker.setLastName(qcc.getLastName());
        qualityChecker.setFirstName(qcc.getFirstName());

      /*  if(qcc.getSpirit() != null && qcc.getSpirit().size() > 0){
            qcc.getSpirit().forEach(spirit -> qualityChecker.getSpirits()
                    .add(spiritCommandToSpirits.convert(spirit)));
        } */

        return qualityChecker;
    }
}
