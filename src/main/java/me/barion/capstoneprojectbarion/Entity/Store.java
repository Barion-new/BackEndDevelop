package me.barion.capstoneprojectbarion.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Integer storeId;

    @Column(name = "store_name", nullable = false, length = 100)
    private String storeName;

    @Column(name = "store_type", length = 100)
    private String storeType;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "onboarding_status" ,nullable = false)
    private Boolean onboardingStatus;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categories;

    public Store() {}

    public Store(String storeName, String storeType, String password, Boolean onboardingStatus) {
        this.storeName = storeName;
        this.storeType = storeType;
        this.password = password;
        this.onboardingStatus = onboardingStatus;
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", storeType='" + storeType + '\'' +
                ", password='" + password + '\'' +
                ", onboardingStatus=" + onboardingStatus +
                '}';
    }

    public void updateStoreName(String newStoreName) {
        this.storeName = newStoreName;
    }
}
