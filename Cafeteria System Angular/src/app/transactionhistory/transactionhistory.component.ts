import { Component, OnInit } from '@angular/core';
import { TransactionService } from '../transaction.service';
import { UserService } from '../user.service';
import { Router } from"@angular/router"; 

interface Transaction {
  date: Date;
  userid: number;
  workspaceid: number;
  transactiontype: string;
  amount: number;
}
@Component({
  selector: 'app-transactionhistory',
  templateUrl: './transactionhistory.component.html',
  styleUrls: ['./transactionhistory.component.css']
})
export class TransactionhistoryComponent implements OnInit {
  Balance: number = 0; 
  transactions: Transaction[] =[];
  Username: string = "your_username"; 

  constructor(private transactionService: TransactionService,private UserService:UserService,private router: Router) {}
  ngOnInit() {
    this.getTransactions();
    this.getBalance();
  }
  getTransactions() {
    
    this.transactionService.getPersonalTransaction().subscribe(
      (data: Transaction[]) => {
        console.warn(data);
        
        this.transactions = data;
      },
      (error: any) => {
        console.error('Error:', error);
        // Handle the error if necessary
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
  directHome(){
    let roleid = localStorage.getItem("roleid");
    if (roleid === "1") {
      this.router.navigate(['/ahome']);
    } else {
      this.router.navigate(['/home']);
    }
  }
}
