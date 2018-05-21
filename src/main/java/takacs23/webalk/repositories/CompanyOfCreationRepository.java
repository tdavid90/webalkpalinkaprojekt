package takacs23.webalk.repositories;

import org.springframework.data.repository.CrudRepository;
import takacs23.webalk.models.CompanyOfCreation;

import java.util.Optional;

public interface CompanyOfCreationRepository extends CrudRepository<CompanyOfCreation, Long> {

    Optional<CompanyOfCreation> findByCompanyName(String companyName);
}
