package com.universidad.torneo_system.model;

import com.universidad.torneo_system.model.enums.Rol;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String label;

    private String route;

    private String icon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private MenuItem parent;

    @Column(nullable = false)
    private Integer sortOrder = 0;

    @Column(nullable = false)
    private Boolean enabled = true;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "menu_item_roles", joinColumns = @JoinColumn(name = "menu_item_id"))
    @Column(name = "rol")
    private Set<Rol> roles;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public String getRoute() { return route; }
    public void setRoute(String route) { this.route = route; }
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
    public MenuItem getParent() { return parent; }
    public void setParent(MenuItem parent) { this.parent = parent; }
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
    public Boolean getEnabled() { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }
    public Set<Rol> getRoles() { return roles; }
    public void setRoles(Set<Rol> roles) { this.roles = roles; }
}

