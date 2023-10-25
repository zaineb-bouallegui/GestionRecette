import { Component, OnInit } from '@angular/core';
import { Profile } from '../class/profile';
import { ProfileService } from '../services/profile.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-profile',
  templateUrl: './updateprofile.component.html',
  styleUrls: ['./updateprofile.component.css']
})
export class UpdateProfileComponent implements OnInit {

  id!:number;
  profile:Profile=new Profile();

  constructor(private profileService: ProfileService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.profileService.getProfileById(this.id).subscribe(data => {
      this.profile = data;
    }, error => console.log(error));
  }
  onSubmit(){
    this.profileService.updateProfile(this.id, this.profile).subscribe( data =>{
      this.goToProfileList();
    }
    , error => console.log(error));
  }

  goToProfileList(){
    this.router.navigate(['/profile']);
  }
}
