package Capstone.AutoScheduler.global.repository;

import Capstone.AutoScheduler.global.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUserName(String userName);

    boolean existsByUserName(String userName);
}
