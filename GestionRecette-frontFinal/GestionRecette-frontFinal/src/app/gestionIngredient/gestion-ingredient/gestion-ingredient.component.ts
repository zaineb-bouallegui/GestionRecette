import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Ingredient } from 'src/app/class/ingredient';
import { IngredientService } from 'src/app/services/ingredient.service';

@Component({
  selector: 'app-gestion-ingredient',
  templateUrl: './gestion-ingredient.component.html',
  styleUrls: ['./gestion-ingredient.component.css']
})
export class GestionIngredientComponent implements OnInit {

  ingredients!:Ingredient[];
  searchTerm: string = '';
  constructor(private ingredientService: IngredientService,
    private router: Router) { }

  ngOnInit(): void {
    this.getIngredients();
  }

  ingredientDetails(id: number){
    this.router.navigate(['detail', id]);
  }

  private getIngredients(){
    this.ingredientService.getIngredientList().subscribe(data => {
      this.ingredients = data;
    });
  }
  updateIngredient(id: number){
    this.router.navigate(['edit', id]);
  }
  deleteIngredient(id: number){
    this.ingredientService.deleteIngredient(id).subscribe(data => {
      console.log(data);
      this.getIngredients();
    });

}}
