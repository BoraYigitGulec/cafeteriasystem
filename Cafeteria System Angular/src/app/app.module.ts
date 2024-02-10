import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './token.interceptor';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LogsignComponent } from './logsign/logsign.component';
import { HomeComponent } from './home/home.component';
import { TransactionhistoryComponent } from './transactionhistory/transactionhistory.component';
import { AdminhomeComponent } from './adminhome/adminhome.component';
import { AdminyemekkonsoluComponent } from './adminyemekkonsolu/adminyemekkonsolu.component';
import { AdminusersComponent } from './adminusers/adminusers.component';
import { AdminreportsComponent } from './adminreports/adminreports.component';
import { TransactionsComponent } from './transactions/transactions.component';

@NgModule({
  declarations: [
    AppComponent,
    LogsignComponent,
    HomeComponent,
    TransactionhistoryComponent,
    AdminhomeComponent,
    AdminyemekkonsoluComponent,
    AdminusersComponent,
    AdminreportsComponent,
    TransactionsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,

  ],
  providers: [ 
  {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
