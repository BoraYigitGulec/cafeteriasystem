package com.example.YemekhaneB.repository;
import com.example.YemekhaneB.model.FoodTable;
import com.example.YemekhaneB.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface FoodRepository extends JpaRepository<FoodTable,Long> {
    public FoodTable findByDate(LocalDate date);


}