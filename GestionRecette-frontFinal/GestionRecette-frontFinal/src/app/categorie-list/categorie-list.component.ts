import { Component, OnInit } from '@angular/core';
import { Categorie } from '../class/categorie';
import { CategorieService } from '../services/categorie.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-categorie-list',
  templateUrl: './categorie-list.component.html',
  styleUrls: ['./categorie-list.component.css']
})
export class CategorieListComponent implements OnInit {

  categories!:Categorie[];
  constructor(private categorieService:CategorieService,private router:Router) { }

  ngOnInit(): void {
    this.getCategories();
    
  }
  private getCategories(){
    this.categorieService.getCategoriesList().subscribe(data=>{
      this.categories=data;
    });
  }
  updateCategorie(id:number){
    this.router.navigate(['update-categorie',id]);

  }
deleteCategorie(id:number){
  this.categorieService.deleteCategorie(id).subscribe(data=>{
    console.log(data);
    this.getCategories();
    console.log('Categories refreshed.');
  })
}
categorieDetails(id:number){
  this.router.navigate(['categorie-details',id])
}
}
