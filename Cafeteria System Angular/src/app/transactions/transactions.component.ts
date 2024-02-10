import { Component, OnInit } from '@angular/core';
import { TransactionService } from '../transaction.service';
import { UserService } from '../user.service';
import { Router } from"@angular/router"; 
@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent {
Balance: number = 0;
Username: string = "your_username"; 
dailyPurchaseCost: number = 20;
monthlySubscriptionCost: number = 500;
PurchaseRequest: any[] = [];
  PurchaseObj: any = {
    username: '',
    purchaseid: null,
    amount: null,
    workplaceid: 1
  };
constructor(private transactionService: TransactionService,private UserService:UserService,private router: Router) {}

ngOnInit() {
  this.getBalance();
}
OnmonthlyPurchase(){
  this.PurchaseObj.username = localStorage.getItem('username');
  this.PurchaseObj.purchaseid = 5;
  this.PurchaseObj.amount = this.monthlySubscriptionCost;
  this.transactionService.purchaseTransaction(this.PurchaseObj).subscribe(
    
    (response: any) => {
      this.Balance = this.Balance- this.PurchaseObj.amount;
    },
    (error: any) => {
      console.error('Error fetching balance:', error);
    }
  );
}
OndailyPurchase(){
  this.PurchaseObj.username = localStorage.getItem('username');
  this.PurchaseObj.purchaseid = 3;
  this.PurchaseObj.amount = this.dailyPurchaseCost;
  this.transactionService.purchaseTransaction(this.PurchaseObj).subscribe(
    
    (response: any) => {
      console.warn(response);
      this.Balance = this.Balance- this.PurchaseObj.amount;
    },
    (error: any) => {
      console.error('Error fetching balance:', error);
    }
  );
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
      if(username != null){
        this.Username = username;
      }
      this.Balance = response;
    },
    (error: any) => {
      console.error('Error fetching balance:', error);
    }
  );
    
}

directHome(){
  let roleid = localStorage.getItem("roleid");
  if (roleid === "1") {
    this.router.navigate(['/ahome']);
  } else {
    this.router.navigate(['/home']);
  }
}
}
