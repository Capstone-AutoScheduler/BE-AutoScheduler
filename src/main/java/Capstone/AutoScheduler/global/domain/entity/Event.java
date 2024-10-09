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
    @Column(name = "event_id", nullable = false,columnDefinition = "bigint")
    private Long eventId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "event_title", nullable = false, columnDefinition = "varchar(256)")
    private String eventTitle;

    @Column(name = "event_body", nullable = false, columnDefinition = "varchar(256)")
    private String eventBody;

    // 이벤트 시작일
    @Column(name = "start_date", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime startDate;
    // 이벤트 종료일
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


