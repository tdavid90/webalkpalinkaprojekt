package takacs23.webalk.services;

import takacs23.webalk.commands.SpiritCommand;
import takacs23.webalk.models.Spirit;

import java.util.Set;

public interface SpiritService {

    Set<Spirit> getSpirits();

    Spirit findById(long l);

    SpiritCommand saveSpiritCommand(SpiritCommand spiritCommand);

    SpiritCommand findCommandById(long l);

    void deleteById(long l);
}
