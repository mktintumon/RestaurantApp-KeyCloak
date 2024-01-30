package com.keycloak.restaurant.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keycloak.restaurant.entities.MenuItems;

@Repository
public interface MenuItemRepo extends JpaRepository<MenuItems,Long>{
    List<MenuItems> findAllByMenuId(Long id);
}
  