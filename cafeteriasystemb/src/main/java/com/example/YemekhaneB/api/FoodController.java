package com.example.YemekhaneB.api;

import com.example.YemekhaneB.model.FoodTable;
import com.example.YemekhaneB.model.User;
import com.example.YemekhaneB.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//controllerlar bilgileri alır ve servislere gönderir
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/adminyemekkonsolu")
public class FoodController {
    private final FoodService FoodService;
    public FoodController(FoodService FoodService) {
        this.FoodService = FoodService;
    }
    @GetMapping ("/admin/menus")
    public ResponseEntity<List< FoodTable > >getFoods() {
        return  new ResponseEntity<>(FoodService.getFoods(),HttpStatus.OK);
    }
    @GetMapping("/admin/menusorder")
    public ResponseEntity<List<FoodTable>> getorderFoods() {
        List<FoodTable> foods = FoodService.getFoods();

        // Filter out entries with null dates
        List<FoodTable> nonNullFoods = foods.stream()
                .filter(food -> food.getDate() != null)
                .collect(Collectors.toList());

        // Sort the non-null foods in descending order of date
        List<FoodTable> sortedFoods = nonNullFoods.stream()
                .sorted(Comparator.comparing(FoodTable::getDate).reversed())
                .collect(Collectors.toList());

        return new ResponseEntity<>(sortedFoods, HttpStatus.OK);
    }
    @GetMapping ("/{formattedDate}")
    public ResponseEntity<FoodTable> getFoodTable(@PathVariable String formattedDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date;
        try {
            date = LocalDate.parse(formattedDate, formatter);
        } catch (DateTimeParseException e) {
            // Handle the exception if the date format is invalid
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        FoodTable existingFood = FoodService.getByDate(date);

        if (existingFood == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(existingFood, HttpStatus.OK);
    }
    @PostMapping("/admin")
    public ResponseEntity<ApiResponse> saveFood(@RequestBody FoodTable NewMenu){ //neyi geri vereceğimizi yazıyoruz
        FoodTable ExistingFood = FoodService.getByDate(NewMenu.getDate());
        if(ExistingFood!= null){
            FoodService.UpdateFood(NewMenu);
            return new ResponseEntity<>(new ApiResponse("Saved"),HttpStatus.OK);
        }
        FoodService.saveFood(NewMenu);
        return new ResponseEntity<>(new ApiResponse("Saved"),HttpStatus.CREATED); //postta created veriliyo
    }


}
