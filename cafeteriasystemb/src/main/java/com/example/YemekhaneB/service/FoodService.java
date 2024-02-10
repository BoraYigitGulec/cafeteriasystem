package com.example.YemekhaneB.service;

import com.example.YemekhaneB.model.FoodTable;
import com.example.YemekhaneB.model.User;
import com.example.YemekhaneB.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FoodService {
    private final com.example.YemekhaneB.repository.FoodRepository FoodRepository;

    public FoodService(com.example.YemekhaneB.repository.FoodRepository FoodRepository) {
        this.FoodRepository = FoodRepository;
    }
    public FoodTable getByDate(LocalDate date) {
       return FoodRepository.findByDate(date);
    }
    public FoodTable getById(Long Id) {
        return FoodRepository.findById(Id).orElseThrow(() -> new RuntimeException("Food not found"));
    }
    public FoodTable saveFood(FoodTable newMenu) {

        return FoodRepository.save(newMenu);
    }
    public void deleteFood(FoodTable Menu) {
        FoodRepository.deleteById(Menu.getFoodId());
    }

    public  FoodTable UpdateFood(FoodTable Menu){
        FoodTable oldFood = getByDate(Menu.getDate());
        deleteFood(oldFood);
        return saveFood(Menu);
    }
    public List<FoodTable> getFoods() {
        return FoodRepository.findAll();
    }
}
