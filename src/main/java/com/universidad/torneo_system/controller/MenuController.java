package com.universidad.torneo_system.controller;

import com.universidad.torneo_system.dto.response.MenuItemResponse;
import com.universidad.torneo_system.service.MenuService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public List<MenuItemResponse> obtener(Authentication auth) {
        return menuService.obtenerMenuParaAuthorities(auth.getAuthorities());
    }
}

