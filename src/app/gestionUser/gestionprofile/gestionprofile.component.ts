import { ProfileService } from './../../services/profile.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Profile } from 'src/app/class/profile';

@Component({
  selector: 'app-gestion-profile',
  templateUrl: './gestionprofile.component.html',
  styleUrls: ['./gestionprofile.component.css']
})
export class GestionProfileComponent implements OnInit {
  profiles!:Profile[];

  constructor(private profileService: ProfileService,
    private router: Router) { }

  ngOnInit(): void {
    this.getProfiles();
  }

  profileDetails(id: number){
    this.router.navigate(['detail', id]);
  }

  private getProfiles(){
    this.profileService.getProfileList().subscribe(data => {
      this.profiles = data;
    });
  }
  updateProfile(id: number){
    this.router.navigate(['update', id]);
  }
  deleteProfile(id: number){
    this.profileService.deleteProfile(id).subscribe(data => {
      console.log(data);
      this.getProfiles();
    });
    
}}
