package com.example.YemekhaneB.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "workplace")
public class Workplace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("name")
    private String name;

    public Long getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public void setName( String Name){
        this.name = Name;
    }
}
