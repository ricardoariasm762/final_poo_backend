package com.universidad.torneo_system.dto.response;

import java.util.ArrayList;
import java.util.List;

public class MenuItemResponse {

    private Long id;
    private String label;
    private String route;
    private String icon;
    private List<MenuItemResponse> children = new ArrayList<>();

    public MenuItemResponse() {}

    public MenuItemResponse(Long id, String label, String route, String icon) {
        this.id = id;
        this.label = label;
        this.route = route;
        this.icon = icon;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public String getRoute() { return route; }
    public void setRoute(String route) { this.route = route; }
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
    public List<MenuItemResponse> getChildren() { return children; }
    public void setChildren(List<MenuItemResponse> children) { this.children = children; }
}

