package Capstone.AutoScheduler.global.domain.entity;


import Capstone.AutoScheduler.global.converter.JsonConverter;
import Capstone.AutoScheduler.global.domain.common.BaseEntity;
import Capstone.AutoScheduler.global.domain.enums.TypeSource;
import jakarta.persistence.*;
import lombok.*;


import javax.net.ssl.SSLSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "source_id", nullable = false)
//    private Source source;

    @Enumerated(EnumType.STRING)
    @Column(name = "source_type", nullable = false, columnDefinition = "varchar(256)")
    private TypeSource sourceType;

    @Column(name = "generator_title", nullable = false, columnDefinition = "varchar(256)")
    private String generatorTitle;

    @Column(name = "generator_detail", nullable = false, columnDefinition = "varchar(256)")
    private String generatorDetail;

//    // frames 배열(string)
//    @Column(name = "frames", nullable = false, columnDefinition = "TEXT")
//    private String frames;
//    // mapping 배열(string)
//    @Column(name = "mapping", nullable = false, columnDefinition = "TEXT")
//    private String mapping;

    // JSON 데이터를 문자열로 저장
    @Convert(converter = JsonConverter.class)
    @Column(name = "frames", nullable = false, columnDefinition = "TEXT")
    private List<Map<String, Object>> frames;

    @Convert(converter = JsonConverter.class)
    @Column(name = "mapping", nullable = false, columnDefinition = "TEXT")
    private List<Map<String, Object>> mapping;

    // SourceType web일 경우 해당 URL
    @Column(name = "webUrl", nullable = true, columnDefinition = "varchar(512)")
    private String webUrl;

    // 해당 일정 생성기로 생성한 Event 리스트
    @OneToMany(mappedBy = "generator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();

    @OneToMany(mappedBy = "generator", cascade = CascadeType.ALL, orphanRemoval = true,  fetch = FetchType.EAGER)
    private List<Bookmark> bookmarkList = new ArrayList<>();

    public void addEvent(Event event) {
        this.events.add(event);
        event.setGenerator(this);
    }

    public Bookmark getBookmarkByMember(Long memberId) {
        return this.bookmarkList.stream()
                .filter(bookmark -> bookmark.getMember().getMemberId().equals(memberId))
                .findFirst()
                .orElse(null); // 해당 멤버의 북마크가 없을 경우 null 반환
    }


}
