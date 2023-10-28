import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  email: string = '';
  password: string = '';
  isLogin: boolean = true;
  errorMessage: string = '';

  constructor(private router: Router, private http: HttpClient) { }

  ngOnInit(): void {
  }

  login() {
    console.log(this.email);
    console.log(this.password);
    let bodyData = {
      email: this.email,
      password: this.password,
    };

    this.http.post("http://localhost:5000/api/login", bodyData).subscribe((resultData: any) => {
      console.log(resultData);

      if (resultData.message === "User not found. Signup Please") {
        alert("User not found. Please sign up.");
      } else if (resultData.message === "Invalid Email / Password") {
        alert("Invalid Email or Password.");
      } else {
        localStorage.setItem('token',resultData.token);
        this.router.navigateByUrl('/profile');
       console.log(resultData.token)
      }
    });
  }
}
