package com.universidad.torneo_system.repository;

import com.universidad.torneo_system.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByEnabledTrue();
}

