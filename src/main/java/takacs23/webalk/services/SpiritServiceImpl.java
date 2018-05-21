package takacs23.webalk.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import takacs23.webalk.commands.SpiritCommand;
import takacs23.webalk.converters.SpiritCommandToSpirits;
import takacs23.webalk.converters.SpiritToSpiritCommand;
import takacs23.webalk.models.Spirit;
import takacs23.webalk.repositories.SpiritRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class SpiritServiceImpl implements SpiritService {

    private final SpiritRepository spiritRepository;
    private final SpiritCommandToSpirits spiritCommandToSpirits;
    private final SpiritToSpiritCommand spiritToSpiritCommand;

    public SpiritServiceImpl(SpiritRepository spiritRepository,
                             SpiritCommandToSpirits spiritCommandToSpirits,
                             SpiritToSpiritCommand spiritToSpiritCommand) {
        this.spiritRepository = spiritRepository;
        this.spiritCommandToSpirits = spiritCommandToSpirits;
        this.spiritToSpiritCommand = spiritToSpiritCommand;
    }

    @Override
    public Set<Spirit> getSpirits() {
        Set<Spirit> spiritSet = new HashSet<>();
        spiritRepository.findAll().iterator().forEachRemaining(spiritSet::add);
        return spiritSet;
    }

    @Override
    public Spirit findById(long l) {
        Optional<Spirit> spiritOptional = spiritRepository.findById(l);

        if (!spiritOptional.isPresent()){
            throw new RuntimeException("Spirit not found!");
        }

        return spiritOptional.get();
    }

    @Override
    @Transactional
    public SpiritCommand saveSpiritCommand(SpiritCommand spiritCommand) {
        Spirit detachedSpirit = spiritCommandToSpirits.convert(spiritCommand);
        Spirit savedSpirit = spiritRepository.save(detachedSpirit);

        return spiritToSpiritCommand.convert(savedSpirit);
    }

    @Override
    @Transactional
    public SpiritCommand findCommandById(long l) {
        return spiritToSpiritCommand.convert(findById(l));
    }

    @Override
    public void deleteById(long l) {
        spiritRepository.deleteById(l);
    }
}
