package company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    @Query("SELECT u FROM Company u WHERE u.name = ?1")
    Optional<Company> findAppUserByName(String name);
}
