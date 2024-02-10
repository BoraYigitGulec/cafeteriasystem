import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http'; 
import { FoodService } from '../food.service';
interface FoodTable{
  date: Date;
  food1: string;
  calorie1: number;
  food2: string;
  food3:string;
  calorie2: number;
  calorie3: number;
  foodId: number;
}
@Component({
  selector: 'app-adminyemekkonsolu',
  templateUrl: './adminyemekkonsolu.component.html',
  styleUrls: ['./adminyemekkonsolu.component.css']
})
export class AdminyemekkonsoluComponent {
  foods: FoodTable[] = [];

  
  constructor(private FoodService: FoodService) {};
  days: any[] = [
    // Initialize with some data if needed
    { date: new Date().toISOString(), food1: '', calorie1: 0, food2: '', calorie2: 0, food3: '', calorie3: 0 }
  ];

  addRow() {
    this.days.push({
      date: '',
      food1: '',
      calorie1: 0,
      food2: '',
      calorie2: 0,
      food3: '',
      calorie3: 0
    });
  }
  
  submitRows() {
    for (const date of this.days) {
      // Assuming your backend API endpoint to save data is "/api/saveData"
      console.warn(date);
      this.FoodService.saveFood(date).subscribe((result:any)=>{
        console.warn(result)
       if (result.message === "Saved"){
      console.warn("Kaydınız başarıyla tamamlanmıştır!");
      this.getMenu();
    }
    
    else {alert(result.message);
    }
      },(error: any) => {
        console.error("Error:", error);
        alert("Hata oluştu");
      }
      )
    }
  }
  
  ngOnInit() {
    this.getMenu();
  }
  getMenu() {
    this.FoodService.getMenuInOrder().subscribe(
      (data: FoodTable[]) => {
        this.foods = data;
        console.warn(this.foods);
      },
      (error: any) => {
        console.error('Error:', error);
        // Handle the error if necessary
      }
    );
  }
}