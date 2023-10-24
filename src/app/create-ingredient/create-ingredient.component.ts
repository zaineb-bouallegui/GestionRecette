import { IngredientService } from '../services/ingredient.service';
import { Component, OnInit } from '@angular/core';
import { Ingredient } from '../class/ingredient ';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-ingredient',
  templateUrl: './create-ingredient.component.html',
  styleUrls: ['./create-ingredient.component.css']
})
export class CreateIngredientComponent implements OnInit {
  ingredient: Ingredient = new Ingredient();

  constructor(private ingredientService: IngredientService,
    private router: Router) { }

  ngOnInit(): void {
  }
  saveIngredient(){
    this.ingredientService.createIngredient(this.ingredient).subscribe( data =>{
      console.log(data);
      this.goToIngredientList();
    },
    error => console.log(error));
  }
  goToIngredientList(){
    this.router.navigate(['/ingredient']);
  }
  
  onSubmit(){
    console.log(this.ingredient);
    this.saveIngredient();
  }

}
