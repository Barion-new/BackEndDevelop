package me.barion.capstoneprojectbarion.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class MenuOption {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionId;

    private String optionName;
    private int quantity;

    @JsonBackReference // 추가: 순환 참조 방지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;
}
