import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CategorieComponent } from './categorie/categorie.component';
import { CategorieListComponent } from './categorie-list/categorie-list.component';
import { UpdateCategorieComponent } from './update-categorie/update-categorie.component';
import { CategorieDetailsComponent } from './categorie-details/categorie-details.component';
import { ThemeComponent } from './theme/theme.component';
import { ThemeListComponent } from './theme-list/theme-list.component';
import { UpdateThemeComponent } from './update-theme/update-theme.component';
import { ThemeDetailsComponent } from './theme-details/theme-details.component';

const routes: Routes = [
  // { path: 'theme', component: ThemeComponent },
  { path: 'categorie', component: CategorieComponent },
  {path:'categorie-list',component:CategorieListComponent},
  {path:'update-categorie/:id',component:UpdateCategorieComponent},
  {path:'categorie-details/:id',component:CategorieDetailsComponent},

  { path: 'theme', component: ThemeComponent },
  {path:'theme-list',component:ThemeListComponent},
  {path:'update-theme/:id',component:UpdateThemeComponent},
  {path:'theme-details/:id',component:ThemeDetailsComponent},
 

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
