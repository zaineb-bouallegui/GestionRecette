import { Component, OnInit } from '@angular/core';
import { Categorie } from '../class/categorie';
import { Router } from '@angular/router';
import { CategorieService } from '../services/categorie.service';

@Component({
  selector: 'app-categorie',
  templateUrl: './categorie.component.html',
  styleUrls: ['./categorie.component.css']
})
export class CategorieComponent implements OnInit {

  categorie:Categorie=new Categorie();

  constructor(private categorieService:CategorieService,private router:Router) { }

  ngOnInit(): void {
  }
  saveCategorie(){
    this.categorieService.createCategorie(this.categorie).subscribe(data=>{
      console.log(data);
      this.goToCategorieList
    })
  }
  goToCategorieList(){
    this.router.navigate(['categorie-list']);
  }
  onSubmit(){
    console.log(this.categorie);
    this.saveCategorie();
    this.goToCategorieList();
  }

}
