package com.example.YemekhaneB.api;

import com.example.YemekhaneB.model.Role;
import com.example.YemekhaneB.model.Title;
import com.example.YemekhaneB.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/roles")
public class RoleController {
    private final com.example.YemekhaneB.service.RoleService RoleService;
    public RoleController(RoleService RoleService) {
        this.RoleService = RoleService;
    }
    @GetMapping
    public ResponseEntity<List<Role>> getRoles(){
        return new ResponseEntity<>(RoleService.getRoles(), HttpStatus.OK);
    }
}
