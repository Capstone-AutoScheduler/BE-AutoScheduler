package Capstone.AutoScheduler.global.domain.entity;


import Capstone.AutoScheduler.global.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Generator extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "generator_id", nullable = false, columnDefinition = "bigint")
    private Long generatorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_id", nullable = false)
    private Source source;

    @Column(name = "generator_title", nullable = false, columnDefinition = "varchar(256)")
    private String generatorTitle;

    @Column(name = "generator_detail", nullable = false, columnDefinition = "varchar(256)")
    private String generatorDetail;


    // 해당 일정 생성기로 생성한 Event 리스트
    @OneToMany(mappedBy = "generator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();

    @OneToMany(mappedBy = "generator", cascade = CascadeType.ALL, orphanRemoval = true,  fetch = FetchType.EAGER)
    private List<Bookmark> bookmarkList = new ArrayList<>();

    public void addEvent(Event event) {
        this.events.add(event);
        event.setGenerator(this);
    }
}
