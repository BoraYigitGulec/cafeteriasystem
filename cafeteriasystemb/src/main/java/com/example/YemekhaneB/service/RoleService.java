package com.example.YemekhaneB.service;

import com.example.YemekhaneB.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final com.example.YemekhaneB.repository.RoleRepository RoleRepository;

    public RoleService(com.example.YemekhaneB.repository.RoleRepository RoleRepository) {
        this.RoleRepository = RoleRepository;
    }

    public List<Role> getRoles() {
        return RoleRepository.findAll();
    }

    public Role getRoleByRoleId(Long id) {
        Role Role =RoleRepository.findByroleid(id);
        return Role;
    }

    public Role saveRole(Role Role) {
        Role existingRole = getRoleByRoleId(Role.getroleid());

        if (existingRole == null) {
            Role.setroleid(Role.getroleid());
            RoleRepository.save(Role);
            return Role;
        }

        return existingRole;
    }
}
