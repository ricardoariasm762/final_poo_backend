package com.universidad.torneo_system.config;

import com.universidad.torneo_system.model.MenuItem;
import com.universidad.torneo_system.model.enums.Rol;
import com.universidad.torneo_system.repository.MenuItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class MenuDataInitializer {

    @Bean
    public CommandLineRunner menuInit(MenuItemRepository repo) {
        return args -> {
            if (!repo.findAll().isEmpty()) {
                return;
            }

            MenuItem adminRoot = new MenuItem();
            adminRoot.setLabel("Administración");
            adminRoot.setIcon("admin_panel_settings");
            adminRoot.setSortOrder(0);
            adminRoot.setRoles(Set.of(Rol.ROLE_ADMIN));
            repo.save(adminRoot);

            MenuItem dashboard = new MenuItem();
            dashboard.setLabel("Dashboard");
            dashboard.setRoute("/dashboard");
            dashboard.setIcon("dashboard");
            dashboard.setParent(adminRoot);
            dashboard.setSortOrder(0);
            dashboard.setRoles(Set.of(Rol.ROLE_ADMIN));
            repo.save(dashboard);

            MenuItem participantes = new MenuItem();
            participantes.setLabel("Participantes");
            participantes.setRoute("/participantes");
            participantes.setIcon("groups");
            participantes.setParent(adminRoot);
            participantes.setSortOrder(1);
            participantes.setRoles(Set.of(Rol.ROLE_ADMIN));
            repo.save(participantes);

            MenuItem torneos = new MenuItem();
            torneos.setLabel("Torneos");
            torneos.setRoute("/torneos");
            torneos.setIcon("trophy");
            torneos.setSortOrder(1);
            torneos.setRoles(Set.of(Rol.ROLE_ADMIN, Rol.ROLE_ESPECTADOR));
            repo.save(torneos);
        };
    }
}

