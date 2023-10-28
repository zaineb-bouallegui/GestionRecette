import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateProfileComponent } from './createprofile/createprofile.component';

import { DetailsComponent } from './details/details.component';
import { GestionProfileComponent } from './gestionUser/gestionprofile/gestionprofile.component';
import { UpdateProfileComponent } from './updateprofile/updateprofile.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { PlatComponent } from './plat/plat.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CategorieDetailsComponent } from './categorie-details/categorie-details.component';
import { CategorieListComponent } from './categorie-list/categorie-list.component';
import { CategorieComponent } from './categorie/categorie.component';
import { ThemeDetailsComponent } from './theme-details/theme-details.component';
import { ThemeListComponent } from './theme-list/theme-list.component';
import { UpdateCategorieComponent } from './update-categorie/update-categorie.component';
import { UpdateThemeComponent } from './update-theme/update-theme.component';
import { ThemeComponent } from './theme/theme.component';
import { GestionIngredientComponent } from './gestionIngredient/gestion-ingredient/gestion-ingredient.component';
import { CreateIngredientComponent } from './create-ingredient/create-ingredient.component';
import { DetailComponent } from './detail/detail.component';
import { UpdateIngredientComponent } from './update-ingredient/update-ingredient.component';
import { EventCreateComponent } from './event-create/event-create.component';
import { EventUpdateComponent } from './event-update/event-update.component';
import { RecetteComponent } from './recette/recette.component';
import { UpdateRecetteComponent } from './update-recette/update-recette.component';


@NgModule({
  declarations: [
    AppComponent,
    GestionProfileComponent,
    CreateProfileComponent,
    UpdateProfileComponent,
    DetailsComponent,
    RegisterComponent,
    LoginComponent,
    PlatComponent,
    CategorieDetailsComponent,
    CategorieListComponent,
    CategorieComponent,
    ThemeDetailsComponent,
    ThemeListComponent,
    UpdateCategorieComponent,
    UpdateThemeComponent,
    ThemeComponent,
    GestionIngredientComponent,
    CreateIngredientComponent,
    DetailComponent,
    UpdateIngredientComponent,
    EventCreateComponent,
    EventUpdateComponent,
    RecetteComponent,
    UpdateRecetteComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
