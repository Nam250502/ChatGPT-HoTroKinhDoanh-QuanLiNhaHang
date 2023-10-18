package com.hutech.BEFoodStore.service;



import com.hutech.BEFoodStore.model.Role;
import com.hutech.BEFoodStore.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Role getDefaultRole() {
        String roleName = "ROLE_USER";
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Default role not found"));
    }
}
