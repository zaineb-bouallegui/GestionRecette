import { Component, OnInit } from '@angular/core';
import { Ingredient } from '../class/ingredient ';
import { IngredientService } from '../services/ingredient.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-ingredient',
  templateUrl: './update-ingredient.component.html',
  styleUrls: ['./update-ingredient.component.css']
})
export class UpdateIngredientComponent implements OnInit {

  id!:number;
  ingredient:Ingredient=new Ingredient();

  constructor(private ingredientService: IngredientService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.ingredientService.getIngredientById(this.id).subscribe(data => {
      this.ingredient = data;
    }, error => console.log(error));
  }
  onSubmit(){
    this.ingredientService.updateIngredient(this.id, this.ingredient).subscribe( data =>{
      this.goToIngredientList();
    }
    , error => console.log(error));
  }

  goToIngredientList(){
    this.router.navigate(['/ingredient']);
  }
}
