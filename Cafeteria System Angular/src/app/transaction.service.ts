import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
interface Transaction {
  date: Date;
  userid: number;
  workspaceid: number;
  transactiontype: string;
  amount: number;
}
@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  getTransactionUrl = "http://localhost:8080/api/transactions/admin";
  getRevenueUrl= "http://localhost:8080/api/transactions/admin/revenue";
  getPurchaseCountUrl = "http://localhost:8080/api/transactions/admin/pcount";
  getptUrl= "http://localhost:8080/api/transactions";
  purchaseUrl= "http://localhost:8080/api/transactions/purchase";


  constructor(private http:HttpClient) { }
  getTransactions(searchUserId: string): Observable<Transaction[]> {
    if (searchUserId) {
      this.getTransactionUrl+= `?searchUsername=${encodeURIComponent(searchUserId)}`;
    }
    return this.http.get<Transaction[]>(this.getTransactionUrl);
  }
  getRevenue(){
    
    return this.http.get(this.getRevenueUrl);
  }
  getPurchase(){
    return this.http.get(this.getPurchaseCountUrl);
  }
  getPersonalTransaction():Observable<Transaction[]>{
    let username = localStorage.getItem('username');
    return this.http.get<Transaction[]>(`${this.getptUrl}/${username}`);
  }
  purchaseTransaction(purchase: any){
    return this.http.post(this.purchaseUrl,purchase);
  }
}
