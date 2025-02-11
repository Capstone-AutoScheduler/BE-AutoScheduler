package Capstone.AutoScheduler.global.domain.entity;

import Capstone.AutoScheduler.global.domain.common.BaseEntity;
import Capstone.AutoScheduler.global.web.dto.Event.EventRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Event extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false, columnDefinition = "bigint")
    private Long eventId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "generator_id", nullable = true) // null 허용 (일정생성기를 사용하지 않는 경우도 고려)
    private Generator generator;

    @Column(name = "event_title", nullable = false, columnDefinition = "varchar(256)")
    private String eventTitle;

    @Column(name = "event_body", nullable = false, columnDefinition = "varchar(256)")
    private String eventBody;

    @Column(name = "event_color", nullable = false, columnDefinition = "varchar(256)")
    private String eventColor;

    @Column(name = "start_date", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime endDate;


    public void setMember(Member member) {
        this.member = member;
    }

    public void update(EventRequestDTO.UpdateEventDTO request) {
        this.eventTitle = request.getEventTitle();
        this.eventBody = request.getEventBody();
        this.startDate = request.getStartDate();
        this.endDate = request.getEndDate();
    }

}


