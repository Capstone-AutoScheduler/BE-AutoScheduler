package Capstone.AutoScheduler.global.repository;

import Capstone.AutoScheduler.global.domain.entity.Event;
import Capstone.AutoScheduler.global.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByMember(Member member);
    List<Event> findTop10ByMember_MemberIdOrderByCreatedAtDesc(Long memberId);
}
