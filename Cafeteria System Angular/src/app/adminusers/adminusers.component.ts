import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';

interface User {
  id: number;
  username: string;
  password: string;
  balance: number;
  titleId: number;
  roleId: number;
  editMode?: boolean;
  newUsername?: string;
  newPassword?: string;
  newBalance?: number;
  newTitleId?: number;
  newroleid?: number;
}
interface Userv2 {
  id: number;
  username: string;
  password: string;
  balance: number;
  titleId: number;
  roleid?: number;
}

@Component({
  selector: 'app-adminusers',
  templateUrl: './adminusers.component.html',
  styleUrls: ['./adminusers.component.css']
})
export class AdminusersComponent implements OnInit {
  users: User[] = [];
  filteredUsers: User[] = [];
  searchUsername: string = '';
  changeUsers: any[] = [];
  changeObj: any = {
    username: '',
    password: '',
    id: null,
    balance: null,
    title: {
      titleid: null
    },
    role:{
      roleid:null
    }
  };

  constructor(private userService: UserService) {}

  ngOnInit() {
    this.getUsers();
  }

  getUsers() {
    this.userService.getUsers(this.searchUsername).subscribe(
      (data: User[]) => {
        console.warn(data);
        
        this.users = data;
        this.filterUsersByUsername();
      },
      (error: any) => {
        console.error('Error:', error);
        // Handle the error if necessary
      }
    );
  }

  onSearchChange(event: any) {
    this.searchUsername = event;
    this.filterUsersByUsername();
  }

  filterUsersByUsername() {
    // Filter users by username if searchUsername is provided
    if (this.searchUsername.trim() !== '') {
      const lowerCaseSearchUsername = this.searchUsername.toLowerCase();
      this.filteredUsers = this.users.filter(user =>
        user.username.toLowerCase().includes(lowerCaseSearchUsername)
      );
    } else {
      // If searchUsername is empty, display all users
      this.filteredUsers = this.users;
    }
  }

  toggleEditMode(user: User) {
    
    user.editMode = !user.editMode;
  }

  saveChanges(user: User) {
    if (user.newUsername!="" &&user.newUsername!='' && user.newUsername!= null){
      this.changeObj.username = user.newUsername;
      user.username = user.newUsername!;

  }else{this.changeObj.username = null;}
  console.warn(this.changeObj.username);

   if (user.newPassword!='' &&user.newPassword!="" && user.newPassword != null){
      this.changeObj.password = user.newPassword;
      user.password = user.newPassword!;

  }else{this.changeObj.password = null;}

  if (user.newTitleId != null){
      this.changeObj.title.titleid = user.newTitleId;
      user.titleId = user.newTitleId!;

  }else{this.changeObj.titleId = null;}

  if (user.newroleid != null){
    this.changeObj.role.roleid = user.newroleid;
    user.roleId = user.newroleid!;

  }else{this.changeObj.role.roleid = null;}

  

  if (user.newBalance != null){
      this.changeObj.balance = user.newBalance;
      user.balance = user.newBalance!;

  }else{this.changeObj.balance = null;}


  this.changeObj.id = user.id;
  console.warn(this.changeObj);
  this.userService.changeUsers(this.changeObj).subscribe(
    (result: any) => {
      console.warn(result);
      if (result.message === "Successful"){
        console.warn("Successful");
        user.editMode = false;
      }
      else if(result.message ==="Title value should be between 1-5"){
        alert("Title value should be between 1-5");
      }
      else if(result.message ==="Role Id should be 1 for Admins, 0 for Users"){
        alert("Role Id should be 1 for Admins, 0 for Users");
      }
    },
    (error: any) => {
      console.error('Error:', error);
    }
  );

    /*user.username = user.newUsername!;
    user.password = user.newPassword!;
    user.balance = user.newBalance!;
    user.titleId = user.newTitleId!;
    user.editMode = false;*/

    // You may need to call a service method here to update user data on the server
    // For example: this.userService.updateUser(user).subscribe(...);

    // For demonstration purposes, updating locally
    this.filterUsersByUsername();
  }
  
}