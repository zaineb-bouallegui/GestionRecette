import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Theme } from '../class/theme';
import { ThemeService } from '../services/theme.service';

@Component({
  selector: 'app-theme-details',
  templateUrl: './theme-details.component.html',
  styleUrls: ['./theme-details.component.css']
})
export class ThemeDetailsComponent implements OnInit {

  id:number=0;
  theme:Theme=new Theme();
  constructor(private themeService:ThemeService ,private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.id=this.route.snapshot.params['id'];
    this.theme=new Theme();
    this.themeService.getThemeById(this.id).subscribe(data=>{
      this.theme=data;
    })
  }

}