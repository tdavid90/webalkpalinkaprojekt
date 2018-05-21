package takacs23.webalk.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import takacs23.webalk.models.*;
import takacs23.webalk.repositories.CompanyOfCreationRepository;
import takacs23.webalk.repositories.QualityCheckerRepository;
import takacs23.webalk.repositories.SpiritRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SpiritBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final SpiritRepository spiritRepository;
    private final CompanyOfCreationRepository companyOfCreationRepository;
    private final QualityCheckerRepository qualityCheckerRepository;

    public SpiritBootstrap(SpiritRepository spiritRepository,
                           CompanyOfCreationRepository companyOfCreationRepository,
                           QualityCheckerRepository qualityCheckerRepository) {
        this.spiritRepository = spiritRepository;
        this.companyOfCreationRepository = companyOfCreationRepository;
        this.qualityCheckerRepository = qualityCheckerRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        spiritRepository.saveAll(getSpirits());
    }

    private List<Spirit> getSpirits(){

        List<Spirit> spirits = new ArrayList<>();

        //get Companies of Creation
        Optional<CompanyOfCreation> firstCocOptional = companyOfCreationRepository.findByCompanyName("Ez egy cég");

        if(!firstCocOptional.isPresent()){
            throw new RuntimeException("Expected company not found!");
        }

        Optional<CompanyOfCreation> secondCocOptional = companyOfCreationRepository.findByCompanyName("Ez egy másik cég");

        if(!secondCocOptional.isPresent()){
            throw new RuntimeException("Expected company not found!");
        }

        Optional<CompanyOfCreation> thirdCocOptional = companyOfCreationRepository.findByCompanyName("Ez egy harmadik cég");

        if(!thirdCocOptional.isPresent()){
            throw new RuntimeException("Expected company not found!");
        }

        //get optionals
        CompanyOfCreation firstCoc = firstCocOptional.get();
        CompanyOfCreation secondCoc = secondCocOptional.get();
        CompanyOfCreation thirdCoc = thirdCocOptional.get();

        //get quality checkers
        Optional<QualityChecker> pistaQualityCheckerOptional = qualityCheckerRepository.findByFirstName("Pista");

        if(!pistaQualityCheckerOptional.isPresent()){
            throw new RuntimeException("Expected person not found!");
        }

        Optional<QualityChecker> laciQualityCheckerOptional = qualityCheckerRepository.findByFirstName("Laci");

        if(!laciQualityCheckerOptional.isPresent()){
            throw new RuntimeException("Expected person not found!");
        }

        Optional<QualityChecker> feriQualityCheckerOptional = qualityCheckerRepository.findByFirstName("Feri");

        if(!feriQualityCheckerOptional.isPresent()){
            throw new RuntimeException("Expected person not found!");
        }

        QualityChecker pistaQualityChecker = pistaQualityCheckerOptional.get();
        QualityChecker laciQualityChecker = laciQualityCheckerOptional.get();
        QualityChecker feriQualityChecker = feriQualityCheckerOptional.get();

        //alma pálinka ajánlat
        Spirit almaSpirit = new Spirit();
        almaSpirit.setType("alma pálinka");
        almaSpirit.setPercentageOfAlcohol(51);
        almaSpirit.setBrewLocation("Vásárosnamény");
        almaSpirit.setDescription("Ez egy rövid leírás a pálinkáról");
        almaSpirit.setQuality(Quality.GOOD);
        almaSpirit.setQuantity(30);

        BrewProcess almaBrewProcess = new BrewProcess();
        almaBrewProcess.setSpiritBrewProcess("Ez egy hosszú leírás a pálinka készítéséről");
        almaSpirit.setBrewProcess(almaBrewProcess);

        almaSpirit.addUsedResource(new UsedResource("cefre", new BigDecimal(300), firstCoc));
        almaSpirit.addUsedResource(new UsedResource("forrásvíz", new BigDecimal(100), secondCoc));
        almaSpirit.addUsedResource(new UsedResource("tonik", new BigDecimal(1), thirdCoc));

        almaSpirit.getQualityCheckers().add(feriQualityChecker);
        almaSpirit.getQualityCheckers().add(laciQualityChecker);

        spirits.add(almaSpirit);

        //körte pálinka ajánlat
        Spirit korteSpirit = new Spirit();
        korteSpirit.setQuantity(15);
        korteSpirit.setQuality(Quality.AVERGAE);
        korteSpirit.setType("körte pálinka");
        korteSpirit.setDescription("ez az adott ajánlat rövid leírása");
        korteSpirit.setPercentageOfAlcohol(50);
        korteSpirit.setBrewLocation("Szikszó");

        BrewProcess korteBrewProcess = new BrewProcess();
        korteBrewProcess.setSpiritBrewProcess("ez egy hosszú leírás a körte pálinka készítéséről");
        korteSpirit.setBrewProcess(korteBrewProcess);

        korteSpirit.addUsedResource(new UsedResource("cefre", new BigDecimal(200), firstCoc));
        korteSpirit.addUsedResource(new UsedResource("fahéj", new BigDecimal(".05"), secondCoc));
        korteSpirit.addUsedResource(new UsedResource("nyers gyümölcs", new BigDecimal(".15"), thirdCoc));

        korteSpirit.getQualityCheckers().add(laciQualityChecker);
        korteSpirit.getQualityCheckers().add(pistaQualityChecker);
        korteSpirit.getQualityCheckers().add(feriQualityChecker);

        spirits.add(korteSpirit);

        return spirits;
    }
}
