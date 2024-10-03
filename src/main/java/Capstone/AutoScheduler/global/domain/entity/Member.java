package Capstone.AutoScheduler.global.domain.entity;

import Capstone.AutoScheduler.global.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false, columnDefinition = "bigint")
    private Long memberId;

    @Column(name = "email", nullable = false, columnDefinition = "varchar(256)")
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "varchar(256)")
    private String password;

    @Column(name = "nickname", nullable = false, columnDefinition = "varchar(256)")
    private String nickname;

    @Column(name = "birth", columnDefinition = "varchar(256)")
    private String birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public enum Gender {Male, Female}
}
