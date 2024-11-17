package Capstone.AutoScheduler.global.repository;

import Capstone.AutoScheduler.global.domain.entity.Bookmark;
import Capstone.AutoScheduler.global.domain.entity.Generator;
import Capstone.AutoScheduler.global.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    // 북마크 중복 확인
    boolean existsByMemberAndGenerator(Member member, Generator generator);


}
