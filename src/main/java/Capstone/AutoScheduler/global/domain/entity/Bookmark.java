package Capstone.AutoScheduler.global.domain.entity;

import Capstone.AutoScheduler.global.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Bookmark extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id", nullable = false, columnDefinition = "bigint")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "generator_id", nullable = false)
    private Generator generator;


    public void setMember(Member member) {
        if (this.member != null && this.member != member) {
            this.member.getBookmarkList().remove(this);
        }
        this.member = member;
        if (member != null && !member.getBookmarkList().contains(this)) {
            member.getBookmarkList().add(this);
        }
    }

    public void setGenerator(Generator generator) {
        if (this.generator != null && this.generator != generator) {
            this.generator.getBookmarkList().remove(this);
        }
        this.generator = generator;
        if (generator != null && !generator.getBookmarkList().contains(this)) {
            generator.getBookmarkList().add(this);
        }
    }



}
