import { Component, OnInit } from '@angular/core';
import { Theme } from '../class/theme';
import { ActivatedRoute, Router } from '@angular/router';
import { ThemeService } from '../services/theme.service';

@Component({
  selector: 'app-theme-list',
  templateUrl: './theme-list.component.html',
  styleUrls: ['./theme-list.component.css']
})
export class ThemeListComponent implements OnInit {

  themes!:Theme[];
  constructor(private themeService:ThemeService,private router:Router) { }

  ngOnInit(): void {
    this.getThemes();
  }
  private getThemes(){
    this.themeService.getThemesList().subscribe(data=>{
      this.themes=data;
    });
  }
  updateTheme(id:number){
    this.router.navigate(['update-theme',id]);

  }
deleteTheme(id:number){
  this.themeService.deleteTheme(id).subscribe(data=>{
    console.log(data);
    this.getThemes();
    console.log('Themes refreshed.');
  })
}
themeDetails(id:number){
  this.router.navigate(['theme-details',id])
}

}
