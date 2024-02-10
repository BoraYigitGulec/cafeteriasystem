import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http'; // Import the HttpClient module
import { UserService } from '../user.service';
import { Router } from"@angular/router"; // Import the Router module
//import { AuthGuard } from '../AuthGuard';
@Component({
  selector: 'app-logsign',
  templateUrl: './logsign.component.html',
  styleUrls: ['./logsign.component.css']
})

export class LogsignComponent {
  SignUsers: any[] = [];
  SignObj: any = {
    username: '',
    password: '',
  };
  p2Checker: any[] =[];

  p2Obj: any ={
    password2: '',
  }
  LogUsers: any[] = [];
  LoginObj: any = {
    username: '',
    password: '',
  };
  passwordsDoNotMatch = false;
  baseURL = "http://localhost:8080/";
  postuserUrl = "http://localhost:8080/api/";
  constructor(private UserService: UserService, private router: Router) {};//private AuthGuard: AuthGuard


  OnSignUp() {
    if(this.SignObj.password !=="" && this.SignObj.username !== "" && this.p2Obj.password2 !== ""){
      if (this.SignObj.password == this.p2Obj.password2) {
        this.passwordsDoNotMatch = false;
        console.warn(this.SignObj);
        this.UserService.saveUser(this.SignObj).subscribe((result:any)=>{
          console.warn(result)
         if (result != null){
        console.warn("Your registration has been completed successfully!");
        alert("Your registration has been completed successfully!");
      }
      else if(result == null){
        console.warn("UsernameExist");
        alert("The username has been taken, try a different username.");
      }
      else {alert(result.message);
      }
        },(error: any) => {
          console.error("Error:", error);
          alert("An error occurred");
        }
        )
        
      } else {
        this.passwordsDoNotMatch = true;
      }
    }
    this.SignObj = {
      username: '',
      password: '',
    };
    this.p2Obj = {
      password2: ''

    }
  }

  OnLogin() {
    if(this.LoginObj.password !=="" && this.LoginObj.username !== ""&&
    this.LoginObj.password !==null && this.LoginObj.username !== null){
      localStorage.setItem('username',this.LoginObj.username);
      console.warn(localStorage.getItem('username'));
      console.warn(this.LoginObj);
      this.UserService.loginUser(this.LoginObj).subscribe((result:any)=>{
        console.warn(result);
      if (result != null){
        console.warn("Successful");
        localStorage.setItem('token', result.token);
        this.directUser();
      }
      /*else if(result.message === "AdminSuccessful"){
        console.warn("AdminSuccessful");
        //this.AuthGuard.canAcess = true;
        this.router.navigate(['/adminhome']);
      }*/
      else {
        alert("Check your Username and Password.");

      }
      },
      (error: any) => {
        console.error("Error:", error);
        //this.AuthGuard.canAcess = false;
        alert("Check your Username and Password.");
      }
      )
      this.LoginObj = {
        username: '',
        password: '',
      };
    }    
   }
   directUser() {
    this.UserService.getroleName().subscribe(
      (result: any) => {
        console.log("Response from server:", result); // Log the response to the console
        localStorage.setItem("roleid",result.toString());
        if (result ==1) {
          this.router.navigate(['/ahome']);
        } else {
          this.router.navigate(['/home']);
        }
      },
      (error) => {
        console.error("Error:", error); // Log any errors to the console
      }
    );
  }
}

