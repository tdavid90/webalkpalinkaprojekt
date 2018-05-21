package takacs23.webalk.services;

import takacs23.webalk.commands.UsedResourceCommand;

public interface UsedResourceService {

    UsedResourceCommand findBySpiritIdAndUsedResourceId(Long spiritId, Long usedResourceId);

    UsedResourceCommand saveUsedResourceCommand(UsedResourceCommand command);

    void deleteBySpiritIdAndUsedResourceId(Long spiritId, Long usedResourceId);

}
