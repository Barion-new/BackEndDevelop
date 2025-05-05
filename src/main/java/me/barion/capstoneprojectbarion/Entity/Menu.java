package me.barion.capstoneprojectbarion.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Menu {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    private int category;
    private int orderId;

    @Column(length = 100, nullable = false)
    private String menuName;

    private int price;
    private int cost;

    @Column(length = 100)
    private String menuPresent;

    @Column(length = 255)
    private String menuImage;

    @JsonManagedReference // 추가: 순환 참조 방지
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuOption> options;
}
