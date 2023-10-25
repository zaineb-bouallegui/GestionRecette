import { Component, OnInit } from '@angular/core';
import { Ingredient } from '../class/ingredient';
import { ActivatedRoute, Router } from '@angular/router';
import { IngredientService } from '../services/ingredient.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {

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
