package com.example.YemekhaneB.repository;

import com.example.YemekhaneB.model.Title;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<Title,Long> {
    public Title findBytitleid(Long titleid);
}
