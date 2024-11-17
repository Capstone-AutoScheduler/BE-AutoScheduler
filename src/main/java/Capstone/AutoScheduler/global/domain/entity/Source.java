package Capstone.AutoScheduler.global.domain.entity;

import Capstone.AutoScheduler.global.domain.common.BaseEntity;
import Capstone.AutoScheduler.global.domain.enums.TypeSource;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.Type;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Source extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "source_id", nullable = false, columnDefinition = "bigint")
    private Long sourceId;

    @Column(name = "source_Name", nullable = false, columnDefinition = "varchar(256)")
    private String sourceName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(name = "source_type", nullable = false, columnDefinition = "varchar(256) default 'PDF'")
    private TypeSource sourceType = TypeSource.PDF;

}
