import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
interface User {
  id: number;
  username: string;
  password: string;
  balance: number;
  titleId: number;
  roleId: number;
}
@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseURL = "http://localhost:8080/"; // Corrected the variable name
  postuserUrl = "http://localhost:8080/api/";
  loginUrl = " http://localhost:8080/api/auth/login";
  changeUrl = "http://localhost:8080/api/adminu";
  getUserUrl = "http://localhost:8080/api/getUser";
  createUrl = "http://localhost:8080/api/auth/create";
  balanaceUrl= "http://localhost:8080/api/balance";
  roleUrl="http://localhost:8080/api/role";
  constructor(private http:HttpClient) { }
  saveUser(user:any){
    return this.http.post(this.createUrl,user)
  }
  loginUser(user:any){
    return this.http.post(this.loginUrl,user)
  }
  getUsers(searchUsername: string): Observable<User[]> {
    let usersUrl = `${this.baseURL}api/users`;
    if (searchUsername) {
      usersUrl+= `?searchUsername=${encodeURIComponent(searchUsername)}`;
    }
    return this.http.get<User[]>(usersUrl);
  }
  changeUsers(user:any){
    return this.http.put(this.changeUrl, user);
  }
  getUser(id: number){
    const apiUrl = `${this.baseURL}api/${id}`;
    return this.http.get<User[]>(apiUrl);
  }
    getBalance(){
    let username = localStorage.getItem('username');

    const body = { username: username }; 
    console.warn(body);
    return this.http.get(`${this.balanaceUrl}/${username}`);
  }
  getroleName(){
    let username = localStorage.getItem('username');

    return this.http.get<number>(`${this.roleUrl}/${username}`);
  }
}
