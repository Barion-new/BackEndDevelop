package me.barion.capstoneprojectbarion.repository;

import me.barion.capstoneprojectbarion.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // 기본 CRUD 메소드 자동 제공
}
