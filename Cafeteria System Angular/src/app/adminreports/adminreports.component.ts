import { Component, OnInit } from '@angular/core';
import { TransactionService } from '../transaction.service';
interface Transaction {
  date: Date;
  userid: number;
  workspaceid: number;
  transactiontype: string;
  amount: number;
}
@Component({
  selector: 'app-adminreports',
  templateUrl: './adminreports.component.html',
  styleUrls: ['./adminreports.component.css']
})
export class AdminreportsComponent implements OnInit {
  transactions: Transaction[] =[];
  filteredTransactions: Transaction[]=[];
  searchuserId: string = '';
  constructor(private transactionService: TransactionService) {}

  ngOnInit() {
    this.getTransactions();
    this.getRevenue();
    this.getPurchase();
  }
  dailyPurchase: number = 0; 
  dailyRevenue: number = 0;
  getTransactions() {
    this.transactionService.getTransactions(this.searchuserId).subscribe(
      (data: Transaction[]) => {
        console.warn(data);
        
        this.transactions = data;
        this.filterTransactionsbyId();
      },
      (error: any) => {
        console.error('Error:', error);
        // Handle the error if necessary
      }
    );
  }
  onSearchChange(event: any) {
    this.searchuserId = event;
    this.filterTransactionsbyId();
  }
  filterTransactionsbyId() {
    // Filter users by username if searchUsername is provided
    if (this.searchuserId.trim() !== '') {
      const lowerCaseSearchUsername = this.searchuserId.toLowerCase();
      this.filteredTransactions = this.transactions.filter(transaction =>
        transaction.userid.toString().toLowerCase().includes(lowerCaseSearchUsername)
      );
    } else {
      // If searchUsername is empty, display all users
      this.filteredTransactions = this.transactions;
    }
  }

  getRevenue(){
    this.transactionService.getRevenue().subscribe(
      (response: any) => {
        this.dailyRevenue = response;
      },
      (error: any) => {
        console.error('Error fetching revenue:', error);
      }
    );
  }
  getPurchase(){
    this.transactionService.getPurchase().subscribe(
      (response: any) => {
        this.dailyPurchase = response;
      },
      (error: any) => {
        console.error('Error fetching purchase:', error);
      }
    );
  }
}

