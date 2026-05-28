package com.universidad.torneo_system.service;

import com.universidad.torneo_system.dto.response.MenuItemResponse;
import com.universidad.torneo_system.model.MenuItem;
import com.universidad.torneo_system.model.enums.Rol;
import com.universidad.torneo_system.repository.MenuItemRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuService {

    private final MenuItemRepository repo;

    public MenuService(MenuItemRepository repo) {
        this.repo = repo;
    }

    public List<MenuItemResponse> obtenerMenuParaAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> authSet = new HashSet<>();
        for (GrantedAuthority a : authorities) {
            authSet.add(a.getAuthority());
        }

        List<MenuItem> enabled = repo.findByEnabledTrue();
        List<MenuItem> allowed = enabled.stream().filter(i -> isAllowed(i, authSet)).toList();

        Map<Long, List<MenuItem>> childrenByParent = new HashMap<>();
        List<MenuItem> roots = new ArrayList<>();

        for (MenuItem item : allowed) {
            Long parentId = item.getParent() == null ? null : item.getParent().getId();
            if (parentId == null) {
                roots.add(item);
            } else {
                childrenByParent.computeIfAbsent(parentId, k -> new ArrayList<>()).add(item);
            }
        }

        roots.sort(Comparator.comparing(MenuItem::getSortOrder).thenComparing(MenuItem::getId));
        for (List<MenuItem> kids : childrenByParent.values()) {
            kids.sort(Comparator.comparing(MenuItem::getSortOrder).thenComparing(MenuItem::getId));
        }

        List<MenuItemResponse> result = new ArrayList<>();
        for (MenuItem root : roots) {
            result.add(toTree(root, childrenByParent));
        }
        return result;
    }

    private boolean isAllowed(MenuItem item, Set<String> authSet) {
        Set<Rol> roles = item.getRoles();
        if (roles == null || roles.isEmpty()) {
            return true;
        }
        for (Rol r : roles) {
            if (authSet.contains(r.name())) {
                return true;
            }
        }
        return false;
    }

    private MenuItemResponse toTree(MenuItem item, Map<Long, List<MenuItem>> childrenByParent) {
        MenuItemResponse dto = new MenuItemResponse(item.getId(), item.getLabel(), item.getRoute(), item.getIcon());
        List<MenuItem> children = childrenByParent.getOrDefault(item.getId(), List.of());
        for (MenuItem child : children) {
            dto.getChildren().add(toTree(child, childrenByParent));
        }
        return dto;
    }
}

