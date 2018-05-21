package takacs23.webalk.repositories;

import org.springframework.data.repository.CrudRepository;
import takacs23.webalk.models.QualityChecker;

import java.util.Optional;

public interface QualityCheckerRepository extends CrudRepository<QualityChecker, Long> {

    Optional<QualityChecker> findByFirstName(String firstName);
}
