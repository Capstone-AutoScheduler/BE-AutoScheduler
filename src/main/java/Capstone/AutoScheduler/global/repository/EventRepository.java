package Capstone.AutoScheduler.global.repository;

import Capstone.AutoScheduler.global.domain.entity.Event;
import Capstone.AutoScheduler.global.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByMember(Member member);
    List<Event> findTop10ByMember_MemberIdOrderByCreatedAtDesc(Long memberId);

    @Query("SELECT e FROM Event e WHERE e.member.id = :memberId " +
            "AND ((:startDate <= e.endDate AND :endDate >= e.startDate))")
    List<Event> findOverlappingEvents(@Param("memberId") Long memberId,
                                      @Param("startDate") LocalDateTime startDate,
                                      @Param("endDate") LocalDateTime endDate);
}
