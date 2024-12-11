package Capstone.AutoScheduler.global.repository;

import Capstone.AutoScheduler.global.domain.entity.Generator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneratorRepository extends JpaRepository<Generator, Long> {

    List<Generator> findAllByOrderByGeneratorIdDesc();

    @Query("SELECT g, " +
            "CASE WHEN b.member.id = :memberId THEN true ELSE false END AS isBookmarked " +
            "FROM Generator g " +
            "LEFT JOIN g.bookmarkList b ON b.member.id = :memberId " +
            "ORDER BY g.generatorId DESC")
    List<Object[]> findAllWithBookmarkStatus(@Param("memberId") Long memberId);

}
