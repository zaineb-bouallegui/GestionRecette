import { ProfileService } from './../services/profile.service';
import { Component, OnInit } from '@angular/core';
import { Profile } from '../class/profile';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {
  id!: number;
  profile: Profile = new Profile();
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
      this.getProfileList();
    }
    , error => console.log(error));
  }
  getProfileList(){
    this.router.navigate(['/employees']);
  }
}
