import { Component, OnInit } from '@angular/core';
import { Categorie } from '../categorie';
import { ActivatedRoute } from '@angular/router';
import { CategorieService } from '../categorie.service';

@Component({
  selector: 'app-categorie-details',
  templateUrl: './categorie-details.component.html',
  styleUrls: ['./categorie-details.component.css']
})
export class CategorieDetailsComponent implements OnInit {
  id:number=0;
  categorie:Categorie=new Categorie();


  constructor(private route:ActivatedRoute,private categorieService:CategorieService) { }

  ngOnInit(): void {
    this.id=this.route.snapshot.params['id'];
    this.categorie=new Categorie();
    this.categorieService.getCategorieById(this.id).subscribe(data=>{
      this.categorie=data;
    })
  }

}
