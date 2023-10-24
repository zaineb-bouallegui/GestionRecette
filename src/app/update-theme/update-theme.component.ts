import { Component, OnInit } from '@angular/core';
import { Theme } from '../theme';
import { ActivatedRoute, Router } from '@angular/router';
import { ThemeService } from '../theme.service';

@Component({
  selector: 'app-update-theme',
  templateUrl: './update-theme.component.html',
  styleUrls: ['./update-theme.component.css']
})
export class UpdateThemeComponent implements OnInit {
  id: number=0;
  theme:Theme= new Theme();
  constructor(private themeService:ThemeService,
    private route:ActivatedRoute,
    private router:Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.themeService.getThemeById(this.id).subscribe(data => {
      this.theme = data;
    }, error => console.log(error));
  }

  onSubmit(){
    this.themeService.updateTheme(this.id, this.theme).subscribe( data =>{
      this.goToThemeList();
    }
    , error => console.log(error));
  }

  goToThemeList(){
    this.router.navigate(['/theme-list']);
  }
}

