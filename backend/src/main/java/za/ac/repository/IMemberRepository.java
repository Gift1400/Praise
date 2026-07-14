package za.ac.repository;

import za.ac.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMemberRepository extends JpaRepository<Member, String> {
}
