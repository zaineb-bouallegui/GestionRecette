import { IngredientService } from '../services/ingredient.service';
import { Component, OnInit } from '@angular/core';
import { Ingredient } from '../class/ingredient ';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {
  id: number=0;
  ingredient: Ingredient = new Ingredient();
  constructor(private ingredientService: IngredientService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
this.ingredient=new Ingredient();
    this.ingredientService.getIngredientById(this.id).subscribe(data => {
      this.ingredient = data;
    }, error => console.log(error));
  }

}
