package com.example.YemekhaneB.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "title")
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("titleid")
    @Column(unique = true)
    private Long titleid;

    @JsonProperty("titleName")
    private String titleName;

    public Title() {
    }

    public Title(Long id) {
        this.titleid = id;
        this.titleName = mapIdToTitleName(id);
    }
    private String mapIdToTitleName(Long id) {
        switch (id.intValue()) {
            case 1:
                return "Paid";
            case 2:
                return "Free";
            case 3:
                return "Daily";
            case 4:
                return "Guest";
            case 5:
                return "Subscriber";
            default:
                throw new IllegalArgumentException("Invalid id for Title: " + id);
        }
    }
    public Long getId(){ return id;}
    public Long gettitleid(){return titleid;}
    public String getTitleName(){return titleName;}
    public void setTitleId(Long titleid) {
        this.titleid = titleid;
        this.titleName = mapIdToTitleName(titleid);
    }}