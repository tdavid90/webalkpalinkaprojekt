package takacs23.webalk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import takacs23.webalk.services.SpiritService;


@Controller
public class IndexController {

    private final SpiritService spiritService;

    public IndexController(SpiritService spiritService) {
        this.spiritService = spiritService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model)
    {
        model.addAttribute("spirits", spiritService.getSpirits());

        return "index";
    }

    /*  Optional<QualityChecker> qualityCheckerOptional = qualityCheckerRepository.findByFirstName("Pista");
        Optional<CompanyOfCreation> companyOfCreationOptional = companyOfCreationRepository.findByCompanyName("Ez egy harmadik cég");

        System.out.println("QC id: " + qualityCheckerOptional.get().getId());
        System.out.println("COC id: " + companyOfCreationOptional.get().getId()); */
}
