import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  name: string ="";
  email: string ="";
  password: string ="";

  constructor(private http: HttpClient,private router: Router
    ) { }

  ngOnInit(): void {
  }
  register()
  {
    let bodyData = 
    {
      "name" : this.name,
      "email" : this.email,
      "password" : this.password,
    };
    this.http.post("http://localhost:5000/api/signup",bodyData, { withCredentials: true, responseType: 'text' }).subscribe((resultData: any)=>
    {
        console.log(resultData);
        alert("Student Registered Successfully")
    });
  }
  save()
  {
    this.register();
    this.router.navigate(['/login']);
  }
}
