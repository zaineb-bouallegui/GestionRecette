import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GestionIngredientComponent } from './GestionIngredient/gestion-ingredient/gestion-ingredientcomponent';
import { CreateIngredientComponent } from './create-ingredient/create-ingredient.component';
import { UpdateIngredientComponent } from './update-ingredient/update-ingredient.component';
import { DetailsComponent } from './details/details.component';

const routes: Routes = [
  {path:'ingredient',component: GestionIngredientComponent},
  {path: 'createIngredient', component: CreateIngredientComponent},
  {path:'',redirectTo:'ingredient',pathMatch:'full'},
  {path: 'update/:id', component: UpdateIngredientComponent},
  {path:'detail/:id',component:DetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
