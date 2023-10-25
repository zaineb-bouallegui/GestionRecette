import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GestionProfileComponent } from './gestionUser/gestionprofile/gestionprofile.component';
import { CreateProfileComponent } from './createprofile/createprofile.component';
import { UpdateProfileComponent } from './updateprofile/updateprofile.component';
import { DetailsComponent } from './details/details.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { PlatComponent } from './plat/plat.component';
import { CategorieComponent } from './categorie/categorie.component';
import { CategorieListComponent } from './categorie-list/categorie-list.component';
import { UpdateCategorieComponent } from './update-categorie/update-categorie.component';
import { CategorieDetailsComponent } from './categorie-details/categorie-details.component';
import { ThemeComponent } from './theme/theme.component';
import { ThemeListComponent } from './theme-list/theme-list.component';
import { UpdateThemeComponent } from './update-theme/update-theme.component';
import { ThemeDetailsComponent } from './theme-details/theme-details.component';
import { GestionIngredientComponent } from './gestionIngredient/gestion-ingredient/gestion-ingredient.component';
import { CreateIngredientComponent } from './create-ingredient/create-ingredient.component';
import { UpdateIngredientComponent } from './update-ingredient/update-ingredient.component';
import { DetailComponent } from './detail/detail.component';
import { EventCreateComponent } from './event-create/event-create.component';
import { EventUpdateComponent } from './event-update/event-update.component';
import { RecetteComponent } from './recette/recette.component';
import { UpdateRecetteComponent } from './update-recette/update-recette.component';


const routes: Routes = [
  {path:'profile',component: GestionProfileComponent},
  {path: 'createProfile', component: CreateProfileComponent},
  {path:'',redirectTo:'profile',pathMatch:'full'},
  {path: 'update/:id', component: UpdateProfileComponent},
  {path:'detail/:id',component:DetailsComponent},
  {path:'signup',component:RegisterComponent},
  {path:'login',component:LoginComponent},
  {path:'plat',component:PlatComponent},
  { path: 'categorie', component: CategorieComponent },
  {path:'categorie-list',component:CategorieListComponent},
  {path:'update-categorie/:id',component:UpdateCategorieComponent},
  {path:'categorie-details/:id',component:CategorieDetailsComponent},

  { path: 'theme', component: ThemeComponent },
  {path:'theme-list',component:ThemeListComponent},
  {path:'update-theme/:id',component:UpdateThemeComponent},
  {path:'theme-details/:id',component:ThemeDetailsComponent},
  {path:'ingredient',component: GestionIngredientComponent},
  {path: 'createIngredient', component: CreateIngredientComponent},
  {path:'',redirectTo:'ingredient',pathMatch:'full'},
  {path: 'update/:id', component: UpdateIngredientComponent},
  {path:'detail/:id',component:DetailComponent},
  { path: 'event-create', component: EventCreateComponent },
  { path: 'event-update/:id', component: EventUpdateComponent },
  {path:"recette",component:RecetteComponent},
  {path:"recette/updateRecette/:id",component:UpdateRecetteComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
