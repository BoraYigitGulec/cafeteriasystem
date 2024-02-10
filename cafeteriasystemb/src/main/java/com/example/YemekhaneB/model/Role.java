package com.example.YemekhaneB.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("roleid")
    @Column(unique = true)
    private Long roleid;

    @JsonProperty("rolename")
    private String rolename;

    public Role() {
    }

    public Role(Long roleid) {

        this.roleid = roleid;
        if(roleid !=null){
        this.rolename = mapIdToRoleName(roleid);}else{this.rolename = "User";}
    }
    private String mapIdToRoleName(Long roleid) {
        switch (roleid.intValue()) {
            case 1:
                return "Admin";
            default:
                return "User";
        }
    }
    public Long getId(){ return id;}
    public Long getroleid(){return roleid;}
    public String getrolename(){return rolename;}
    public void setroleid(Long roleid) {
      this.roleid = roleid;
        if(roleid !=null){
            this.rolename = mapIdToRoleName(roleid);}else{this.rolename = "User";}
    }}