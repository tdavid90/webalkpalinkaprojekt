package takacs23.webalk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import takacs23.webalk.commands.SpiritCommand;
import takacs23.webalk.services.SpiritService;

import javax.validation.Valid;

@Controller
public class SpiritController {

    private final SpiritService spiritService;

    public SpiritController(SpiritService spiritService) {
        this.spiritService = spiritService;
    }

    @GetMapping
    @RequestMapping("/spirit/{id}/showspirits")
    public String viewById(@PathVariable String id, Model model){

        model.addAttribute("spirit", spiritService.findById(new Long(id)));

        return "spirit/showspirits";
    }

    @GetMapping
    @RequestMapping("spirit/new")
    public String newSpirit(Model model){
        model.addAttribute("spirit", new SpiritCommand());

        return "spirit/formspirit";
    }
    @PostMapping
    @RequestMapping("spirit")
    public String saveOrUpdateSpirit(@Valid @ModelAttribute("spirit") SpiritCommand spiritCommand, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "spirit/formspirit";
        }

        SpiritCommand savedSpiritCommand = spiritService.saveSpiritCommand(spiritCommand);

        return "redirect:/spirit/" + savedSpiritCommand.getId() + "/showspirits";
    }

    @GetMapping
    @RequestMapping("spirit/{id}/update")
    public String updateSpirit(@PathVariable String id, Model model){
        model.addAttribute("spirit", spiritService.findCommandById(Long.valueOf(id)));
        return "spirit/formspirit";
    }

    @GetMapping
    @RequestMapping("spirit/{id}/delete")
    public String deleteById(@PathVariable String id){
        spiritService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
}
