package Capstone.AutoScheduler.global.repository;

import Capstone.AutoScheduler.global.domain.entity.Event;
import Capstone.AutoScheduler.global.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByMember(Member member);

}
