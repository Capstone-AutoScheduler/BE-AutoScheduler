package Capstone.AutoScheduler.global.repository;

import Capstone.AutoScheduler.global.domain.entity.Generator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneratorRepository extends JpaRepository<Generator, Long> {

    List<Generator> findAllByOrderByGeneratorIdDesc();
}
