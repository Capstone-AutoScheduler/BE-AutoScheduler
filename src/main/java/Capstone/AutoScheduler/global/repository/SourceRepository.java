package Capstone.AutoScheduler.global.repository;

import Capstone.AutoScheduler.global.domain.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<Source, Long> {

    //Source findBySourceName(String sourceName);
    //boolean existsBySourceName(String sourceName);
}
