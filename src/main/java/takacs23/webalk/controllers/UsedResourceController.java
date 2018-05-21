package takacs23.webalk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import takacs23.webalk.commands.CompanyOfCreationCommand;
import takacs23.webalk.commands.SpiritCommand;
import takacs23.webalk.commands.UsedResourceCommand;
import takacs23.webalk.services.CompanyOfCreationService;
import takacs23.webalk.services.SpiritService;
import takacs23.webalk.services.UsedResourceService;

@Controller
public class UsedResourceController {

    private final UsedResourceService usedResourceService;
    private final SpiritService spiritService;
    private final CompanyOfCreationService companyOfCreationService;

    public UsedResourceController(UsedResourceService usedResourceService,
                                  SpiritService spiritService,
                                  CompanyOfCreationService companyOfCreationService) {
        this.usedResourceService = usedResourceService;
        this.spiritService = spiritService;
        this.companyOfCreationService = companyOfCreationService;
    }

    @GetMapping("/spirit/{spiritId}/usedresources")
    public String listUsedResources(@PathVariable String spiritId, Model model){
        model.addAttribute("spirit", spiritService.findCommandById(Long.valueOf(spiritId)));

        return "spirit/usedresource/usedresourcelist";
    }

    @GetMapping("/spirit/{spiritId}/usedresource/{id}/show")
    public String showUsedResource(@PathVariable String spiritId, @PathVariable String id, Model model){
        model.addAttribute("usedResource", usedResourceService
                .findBySpiritIdAndUsedResourceId(Long.valueOf(spiritId), Long.valueOf(id)));

        return "spirit/usedresource/usedresourceshow";
    }

    @GetMapping("/spirit/{spiritId}/usedresource/new")
    public String newRecipe(@PathVariable String spiritId, Model model){

        SpiritCommand spiritCommand = spiritService.findCommandById(Long.valueOf(spiritId));

        UsedResourceCommand usedResourceCommand = new UsedResourceCommand();
        usedResourceCommand.setSpiritId(Long.valueOf(spiritId));
        model.addAttribute("usedResource", usedResourceCommand);

        usedResourceCommand.setCompanyOfCreation(new CompanyOfCreationCommand());

        model.addAttribute("companyOfCreationList", companyOfCreationService.listAllCompanyOfCreation());

        return "spirit/usedresource/formusedresource";
    }

    @GetMapping("/spirit/{spiritId}/usedresource/{id}/update")
    public String updateUsedResource(@PathVariable String spiritId, @PathVariable String id, Model model){
        model.addAttribute("usedResource", usedResourceService
                .findBySpiritIdAndUsedResourceId(Long.valueOf(spiritId), Long.valueOf(id)));
        model.addAttribute("companyOfCreationList", companyOfCreationService.listAllCompanyOfCreation());

        return "spirit/usedresource/formusedresource";
    }
    @PostMapping("spirit/{spiritId}/usedresource")
    public String saveOrUpdateUsedResource(@ModelAttribute UsedResourceCommand command){
        UsedResourceCommand savedCommand = usedResourceService.saveUsedResourceCommand(command);

        return "redirect:/spirit/" + savedCommand.getSpiritId() + "/usedresource/" + savedCommand.getId() + "/show";
    }

    @GetMapping("spirit/{spiritId}/usedresource/{id}/delete")
    public String deleteUsedResource(@PathVariable String spiritId, @PathVariable String id){
        usedResourceService.deleteBySpiritIdAndUsedResourceId(Long.valueOf(spiritId), Long.valueOf(id));

        return "redirect:/spirit/" + spiritId + "/usedresources";
    }


}
