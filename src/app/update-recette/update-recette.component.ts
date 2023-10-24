import { Component, OnInit } from '@angular/core';
import { Recette } from '../Models/recette';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RecetteService } from '../Service/recette.service';


@Component({
  selector: 'app-update-recette',
  templateUrl: './update-recette.component.html',
  styleUrls: ['./update-recette.component.css']
})
export class UpdateRecetteComponent implements OnInit {
  recette:Recette = new Recette();
  recetteList:Recette[]=[];
  id!:number;
  message="";
  ajout=false;

  updateForm=new FormGroup({
    title: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
    category: new FormControl('', [Validators.required]),
    instructions: new FormControl('', [Validators.required])

  })


  constructor(private recetteService:RecetteService,private R:Router ,private actR:ActivatedRoute) { }

  ngOnInit(): void {
    this.actR.paramMap.subscribe(data => this.id = Number(data.get('id')));
    console.log(this.id);
    this.getRecetteById();
  }

   getRecetteById(){
    this.recetteService.getRecetteById(this.id).subscribe(data =>{this.recetteList = data
      console.log(data)
    });
   
  }

  updateRec(){
    console.log(this.updateForm);
    let rec = this.updateForm.getRawValue();
    console.log("rec = ", rec);
    this.recetteService.updateRecette(this.id,rec).subscribe(()=>
    this.R.navigate(['recette']));

  }
  get title(){
    return this.updateForm.get('title');
  }
  get description(){
    return this.updateForm.get('description');
  }
  get instructions(){
    return this.updateForm.get('instructions');
  }
  get category(){
    return this.updateForm.get('category');
  }


}
