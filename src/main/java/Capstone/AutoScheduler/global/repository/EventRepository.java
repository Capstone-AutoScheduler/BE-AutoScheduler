package Capstone.AutoScheduler.global.repository;

import Capstone.AutoScheduler.global.domain.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
