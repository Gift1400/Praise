package za.ac.repository;

import za.ac.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IMemberRepository extends JpaRepository<Member, String> {

}
