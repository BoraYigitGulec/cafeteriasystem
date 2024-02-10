package com.example.YemekhaneB.repository;

import com.example.YemekhaneB.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findByroleid(Long roleid);
}
