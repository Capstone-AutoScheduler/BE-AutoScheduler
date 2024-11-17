package Capstone.AutoScheduler.global.domain.entity;

import Capstone.AutoScheduler.global.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "name", nullable = false, columnDefinition = "varchar(256)")
    private String name;

    @Column(name = "userName", nullable = false, columnDefinition = "varchar(256)")
    private String userName;

    @Column(name = "password", nullable = false, columnDefinition = "varchar(256)")
    private String password;


    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bookmark> bookmarkList = new ArrayList<>();


}
