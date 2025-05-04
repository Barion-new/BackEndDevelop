package me.barion.capstoneprojectbarion.Entity;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;
}
