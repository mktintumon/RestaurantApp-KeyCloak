package com.keycloak.restaurant.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keycloak.restaurant.entities.Menu;
import com.keycloak.restaurant.entities.MenuItems;
import com.keycloak.restaurant.entities.Restaurant;
import com.keycloak.restaurant.repositories.MenuItemRepo;
import com.keycloak.restaurant.repositories.MenuRepo;
import com.keycloak.restaurant.repositories.RestaurantRepo;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping("/restaurant")
@SecurityRequirement(name = "Keycloak")
public class RestaurantController {
	
	@Autowired
	RestaurantRepo restaurantRepo;
	
	@Autowired
	MenuRepo menuRepo;
	
	@Autowired
	MenuItemRepo menuItemRepo;

	@GetMapping
	@RequestMapping("/public/list")
	//Public API
	public List<Restaurant> getRestaurants() {
        return restaurantRepo.findAll();
    }
	
	@GetMapping
	@RequestMapping("/public/menu/{restaurantId}")
	//Public API
	public Menu getMenu(@PathVariable Long restaurantId) {
        Menu menu = menuRepo.findByRestaurantId(restaurantId);
        menu.setMenuItems(menuItemRepo.findAllByMenuId(menu.id));
        return menu;
    }
	
	@PostMapping
	// admin can access (mohit)
	@PreAuthorize("hasRole('admin')")
	public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepo.save(restaurant);
    }
	
	@PostMapping
	@RequestMapping("/menu")
	// manager can access (ram)
	@PreAuthorize("hasRole('Manager')")
	public Menu createMenu(Menu menu) {
		menuRepo.save(menu);
        menu.getMenuItems().forEach(menuItem -> {
            menuItem.setMenuId(menu.id);
            menuItemRepo.save(menuItem);
        });
        return menu;
    }
	
	@PutMapping
	@RequestMapping("/menu/item/{itemId}/{price}")
	// owner can access (suresh)
	@PreAuthorize("hasRole('ceo','Manager')")
	public MenuItems updateMenuItemPrice(@PathVariable Long itemId, @PathVariable BigDecimal price) {
        Optional<MenuItems> menuItem = menuItemRepo.findById(itemId);
        menuItem.get().setPrice(price);
        menuItemRepo.save(menuItem.get());
        return menuItem.get();
	}
}