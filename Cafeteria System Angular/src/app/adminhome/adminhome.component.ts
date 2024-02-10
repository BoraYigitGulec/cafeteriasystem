import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FoodService } from '../food.service';
import { UserService } from '../user.service';
@Component({
  selector: 'app-adminhome',
  templateUrl: './adminhome.component.html',
  styleUrls: ['./adminhome.component.css']
})
export class AdminhomeComponent implements OnInit {
  currentMenu: any; // Declare a variable to hold the menu for the current date

  constructor(private router: Router, private foodService: FoodService,private UserService:UserService) {}

  ngOnInit() {
    this.updateMenu(); // Initial update of the displayed menu
    setInterval(() => this.updateMenu(), this.getMillisecondsUntilNextMidnight());
    this.getBalance();

  }
  Balance: number = 0; 
  Username: string = "your_username"; 

  updateMenu() {
    const currentDate = new Date();
    const formattedDate = this.formatDate(currentDate);
    
    this.foodService.getMenuForDate(formattedDate).subscribe(
      (data: any) => {
        this.currentMenu = data; // Store the fetched menu in the variable
        console.log(this.currentMenu); // Optional: Check the menu data in the console
      },
      (error: any) => {
        console.error('Error:', error);
        // Handle the error if necessary
      }
    );
  }

  // Helper function to format date to yyyy-MM-dd
  formatDate(date: Date): string {
    const year = date.getFullYear();
    const month = this.addLeadingZero(date.getMonth() + 1);
    const day = this.addLeadingZero(date.getDate());
    return `${year}-${month}-${day}`;
  }

  // Helper function to add leading zero to a number if needed
  addLeadingZero(num: number): string {
    return num < 10 ? `0${num}` : num.toString();
  }

  // Helper function to calculate milliseconds until the next midnight
  getMillisecondsUntilNextMidnight(): number {
    const currentDate = new Date();
    const tomorrow = new Date(currentDate);
    tomorrow.setDate(tomorrow.getDate() + 1);
    tomorrow.setHours(0, 0, 0, 0);
    return tomorrow.getTime() - currentDate.getTime();
  }

  AdminPanel() {
    this.router.navigate(['/foodconsole']);
  }

  islemlerpaneli() {
    this.router.navigate(['/transactionhistory']);
  }
  async getBalance(){
    let username = localStorage.getItem('username');
    
    while (!username) {
      console.log("Username not found in local storage. Waiting...");
      await new Promise(resolve => setTimeout(resolve, 1000)); // Wait for 1 second
      username = localStorage.getItem('username');
    }
    this.UserService.getBalance().subscribe(
      
      (response: any) => {
        this.Balance = response;
        if(username != null){
          this.Username = username;
        }
      },
      (error: any) => {
        console.error('Error fetching balance:', error);
      }
    );

  }
}
