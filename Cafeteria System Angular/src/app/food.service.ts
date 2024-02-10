import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
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
@Injectable({
  providedIn: 'root'
})
export class FoodService {
  baseURL = "http://localhost:8080/"; 
  postuserUrl = "http://localhost:8080/api/";
  foodListUrl = " http://localhost:8080/api/adminyemekkonsolu/admin";
  constructor(private http:HttpClient) { }
  saveFood(FoodTable:any){
    return this.http.post(this.foodListUrl,FoodTable)
  }
  getMenuForDate(formattedDate: string) {
    const apiUrl = `${this.baseURL}api/adminyemekkonsolu/${formattedDate}`;
    return this.http.get(apiUrl);
  }
  getMenuInOrder(): Observable<FoodTable[]>{
    const forderUrl =  'http://localhost:8080/api/adminyemekkonsolu/admin/menusorder';
    return this.http.get<FoodTable[]>(forderUrl);
  }
  
}