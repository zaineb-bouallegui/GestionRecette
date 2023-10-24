import { Component, OnInit } from '@angular/core';
import { RecetteService } from '../Service/recette.service';
import { Recette } from '../Models/recette';
import { FormGroup, FormControl, Validators } from '@angular/forms';


@Component({
  selector: 'app-recette',
  templateUrl: './recette.component.html',
  styleUrls: ['./recette.component.css']
})
export class RecetteComponent implements OnInit {
  recetteList:Recette[]=[];

  r:Recette=new Recette();
  message="";
  ajout=false;

  reactiveForm=new FormGroup({
  title: new FormControl('', [Validators.required]),
  description: new FormControl('', [Validators.required]),
  instructions: new FormControl('', [Validators.required]),
  category: new FormControl(''),


})

  constructor(private recetteService:RecetteService) { }

  ngOnInit(): void {
    this.getRecettes()
  }
  getRecettes(){
    this.recetteService.getAllRecettes().subscribe(data =>{this.recetteList = data
      console.log(data)
    });
   
  }

  deleteRec(id:any){

    this.recetteService.deleteRecette(Number(id)).subscribe(()=> this.getRecettes());
    // this.message='Departement supprimé avec succés ! ';
    // this.affichage=true;
  }


  addRec(){
    this.recetteService.addRecette(this.r).subscribe();
    console.log(this.r);
  } 


  saveDep(){
    console.log(this.reactiveForm);
    let rec = this.reactiveForm.getRawValue();
    console.log("rec = ", rec);
    this.recetteService.addRecette(rec).subscribe();
    this.message='Departement ajouté avec succés !'
    this.ajout=true;

  }
  get title(){
    return this.reactiveForm.get('title');
  }
  get description(){
    return this.reactiveForm.get('description');
  }
  get instructions(){
    return this.reactiveForm.get('instructions');
  }
  get category(){
    return this.reactiveForm.get('category');
  }


  calculateStarWidth(averageRating: number): string {
    const maxRating = 5;
      const width = (averageRating / maxRating) * 100 + '%';
    
    return width;
  }



}
