import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { CreateIngredientComponent } from './create-ingredient/create-ingredient.component';
import { FormsModule } from '@angular/forms';
import { DetailsComponent } from './details/details.component';
import { GestionIngredientComponent } from './GestionIngredient/gestion-ingredient/gestion-ingredientcomponent';
import { UpdateIngredientComponent } from './update-ingredient/update-ingredient.component';


@NgModule({
  declarations: [
    AppComponent,
    GestionIngredientComponent,
    CreateIngredientComponent,
    UpdateIngredientComponent,
    DetailsComponent,
    
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
