import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LogsignComponent } from './logsign/logsign.component';
import { TransactionhistoryComponent } from './transactionhistory/transactionhistory.component';
import { AdminhomeComponent } from './adminhome/adminhome.component';
import { AdminyemekkonsoluComponent } from './adminyemekkonsolu/adminyemekkonsolu.component';
import { AdminusersComponent } from './adminusers/adminusers.component';
import { AdminreportsComponent } from './adminreports/adminreports.component';
import { TransactionsComponent } from './transactions/transactions.component';
const routes: Routes = [
  { path: '', component: LogsignComponent },
  { path: 'home', component: HomeComponent, },
  { path: 'transactionhistory', component: TransactionhistoryComponent,},
  { path:'ahome', component :AdminhomeComponent,},
  { path:'foodconsole', component :AdminyemekkonsoluComponent,},
  { path:'ausers', component :AdminusersComponent,},
  { path:'adminreports', component : AdminreportsComponent,},
  { path:'transaction', component : TransactionsComponent,},




];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
