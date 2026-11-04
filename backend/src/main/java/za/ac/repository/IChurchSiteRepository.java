package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.domain.ChurchSite;

@Repository
public interface IChurchSiteRepository extends JpaRepository<ChurchSite, String> {
}
