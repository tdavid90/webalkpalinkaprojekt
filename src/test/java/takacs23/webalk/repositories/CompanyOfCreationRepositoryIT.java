package takacs23.webalk.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import takacs23.webalk.models.CompanyOfCreation;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CompanyOfCreationRepositoryIT {

    @Autowired
    CompanyOfCreationRepository companyOfCreationRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByCompanyName() throws Exception {

        Optional<CompanyOfCreation> companyOfCreationOptional = companyOfCreationRepository.findByCompanyName("Ez egy cég");

        assertEquals("Ez egy cég", companyOfCreationOptional.get().getCompanyName());
    }

    @Test
    public void findByCompanyNameSecond() throws Exception {
        Optional<CompanyOfCreation> companyOfCreationOptional = companyOfCreationRepository.findByCompanyName("Ez egy másik cég");

        assertEquals("Ez egy másik cég", companyOfCreationOptional.get().getCompanyName());
    }

    @Test
    public void findByCompanyNameThird() throws Exception {
        Optional<CompanyOfCreation> companyOfCreationOptional = companyOfCreationRepository.findByCompanyName("Ez egy harmadik cég");

        assertEquals("Ez egy harmadik cég", companyOfCreationOptional.get().getCompanyName());
    }
}