package Capstone.AutoScheduler.global.repository;

import Capstone.AutoScheduler.global.domain.entity.Bookmark;
import Capstone.AutoScheduler.global.domain.entity.Generator;
import Capstone.AutoScheduler.global.domain.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    // 북마크 중복 확인
    boolean existsByMemberAndGenerator(Member member, Generator generator);

    // 회원별 북마크 리스트 조회
    //@Query("SELECT b FROM Bookmark b JOIN FETCH b.generator p LEFT JOIN FETCH p.generatorFileList LEFT JOIN FETCH p.member WHERE b.member = :member")
    List<Bookmark> findAllByMember(Member member);
    @Query("SELECT b.generator.generatorId FROM Bookmark b WHERE b.member.memberId = :memberId")
    List<Long> findBookmarkedGeneratorIdsByMemberId(@Param("memberId") Long memberId);

    @Query("SELECT b.id FROM Bookmark b WHERE b.member.id = :memberId AND b.generator.generatorId = :generatorId")
    Long findBookmarkIdByMemberAndGenerator(@Param("memberId") Long memberId, @Param("generatorId") Long generatorId);

}
