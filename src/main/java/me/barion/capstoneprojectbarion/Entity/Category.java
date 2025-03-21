package me.barion.capstoneprojectbarion.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @Column(name = "store_id", nullable = false)
    private Integer storeId;

    @Column(name = "category_name", nullable = false, length = 100)
    private String categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", insertable = false, updatable = false)
    private Store store;

    public Category() {}

    public Category(Integer storeId, String categoryName) {
        this.storeId = storeId;
        this.categoryName = categoryName;
    }
}
