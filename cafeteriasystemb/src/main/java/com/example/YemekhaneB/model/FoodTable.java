package com.example.YemekhaneB.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "YemekTablosu")
public class FoodTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "date", columnDefinition = "DATE")
    private LocalDate date;

    @Column(name = "food1")
    private String food1;

    @Column(name ="calorie1")
    private Long calorie1;
    @Column(name = "food2")
    private String food2;
    @Column(name = "food3")
    private String food3;
    @Column(name ="calorie2")
    private Long calorie2;
    @Column(name ="calorie3")
    private Long calorie3;
    public FoodTable() {

    }

    public FoodTable(LocalDate date, String food1, String food2,String food3,long calorie1,long calorie2,long calorie3) {
        super();
        this.date = date;
        this.food1 = food1;
        this.calorie1 = calorie1;
        this.food2 = food2;
        this.food3 = food3;
        this.calorie2 = calorie2;
        this.calorie3 = calorie3;
    }
    public Long getFoodId() {
        return Id;
    }
    public LocalDate getDate(){return date;}
    public String getFood1() {
        return food1;
    }
    public void setFood1(String Password) {
        this.food1 = Password;
    }
    public Long getCalorie1() {
        return calorie1;
    }
    public void setCalorie1(Long calorie1) {
        this.calorie1 = calorie1;
    }

    public String getFood2() {
        return food2;
    }

    public void setFood2(String food2) {
        this.food2 = food2;
    }

    public String getFood3() {
        return food3;
    }

    public void setFood3(String food3) {
        this.food3 = food3;
    }

    public Long getCalorie2() {
        return calorie2;
    }

    public void setCalorie2(Long calorie2) {
        this.calorie2 = calorie2;
    }

    public Long getCalorie3() {
        return calorie3;
    }

    public void setCalorie3(Long calorie3) {
        this.calorie3 = calorie3;
    }


}