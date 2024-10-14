package team_alcoholic.jumo_server.domain.liquor.domain;

import jakarta.persistence.*;
import lombok.Getter;
import team_alcoholic.jumo_server.domain.user.domain.User;
import team_alcoholic.jumo_server.global.common.domain.BaseTimeEntity;

@Entity
@Getter
public class LiquorCategoryPrefer extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "liquor_category_id")
    private LiquorCategory liquorCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
