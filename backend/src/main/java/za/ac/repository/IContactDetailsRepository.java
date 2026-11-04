package za.ac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.domain.ContactDetails;

@Repository
public interface IContactDetailsRepository extends JpaRepository<ContactDetails, String> {
}
