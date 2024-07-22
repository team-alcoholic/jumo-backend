package team_alcoholic.jumo_server.meeting.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import team_alcoholic.jumo_server.common.domain.BaseEntity;
import team_alcoholic.jumo_server.common.domain.BaseTimeEntity;

@Entity
@Getter
public class MeetingImage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long meeting;
    private String url;
}
