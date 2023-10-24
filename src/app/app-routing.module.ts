import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RecetteComponent } from './recette/recette.component';
import { HomeComponent } from './home/home.component';
import { UpdateRecetteComponent } from './update-recette/update-recette.component';

const routes: Routes = [
  {path:"home",component:HomeComponent},
  {path:"recette",component:RecetteComponent},
  {path:"recette/updateRecette/:id",component:UpdateRecetteComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
