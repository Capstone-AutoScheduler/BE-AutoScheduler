package Capstone.AutoScheduler.global.repository;

import Capstone.AutoScheduler.global.domain.entity.Generator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneratorRepository extends JpaRepository<Generator, Long> {


}
