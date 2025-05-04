package me.barion.capstoneprojectbarion.repository;


import me.barion.capstoneprojectbarion.Entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Page<Menu> findByCategory(int category, Pageable pageable);
}