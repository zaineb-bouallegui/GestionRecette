import { Component, OnInit } from '@angular/core';
import { Theme } from '../class/theme';
import { Router } from '@angular/router';
import { ThemeService } from '../services/theme.service';

@Component({
  selector: 'app-theme',
  templateUrl: './theme.component.html',
  styleUrls: ['./theme.component.css']
})
export class ThemeComponent implements OnInit {

  theme:Theme=new Theme();
  constructor(private themeService:ThemeService,private router:Router) { }

  ngOnInit(): void {
  }
  saveTheme(){
    this.themeService.createTheme(this.theme).subscribe(data=>{
      console.log(data);
      this.goToThemeList
    })
  }
  goToThemeList(){
    this.router.navigate(['theme-list']);
  }
  onSubmit(){
    console.log(this.theme);
    this.saveTheme();
    this.goToThemeList();
  }

}
