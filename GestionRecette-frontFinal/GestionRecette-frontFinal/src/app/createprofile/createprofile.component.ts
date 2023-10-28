import { ProfileService } from './../services/profile.service';
import { Component, OnInit } from '@angular/core';
import { Profile } from '../class/profile';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-profile',
  templateUrl: './createprofile.component.html',
  styleUrls: ['./createprofile.component.css']
})
export class CreateProfileComponent implements OnInit {
  profile: Profile = new Profile();

  constructor(private profileService: ProfileService,
    private router: Router) { }

  ngOnInit(): void {
  }
  saveProfile(){
    this.profileService.createProfile(this.profile).subscribe( data =>{
      console.log(data);
      this.goToProfileList();
    },
    error => console.log(error));
  }
  goToProfileList(){
    this.router.navigate(['/profile']);
  }
  
  onSubmit(){
    console.log(this.profile);
    this.saveProfile();
  }

}
