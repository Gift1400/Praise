package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.domain.ContactDetails;

public interface IContactDetailsRepository extends JpaRepository<ContactDetails, String> {
}
