package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.domain.ChurchSite;

public interface IChurchSiteRepository extends JpaRepository<ChurchSite, String> {
}
