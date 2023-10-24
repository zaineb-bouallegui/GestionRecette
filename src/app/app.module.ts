import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
// import { ThemeComponent } from './theme/theme/theme.component';
import { RouterModule } from '@angular/router';
import { CategorieComponent } from './categorie/categorie.component';
import { CategorieListComponent } from './categorie-list/categorie-list.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { UpdateCategorieComponent } from './update-categorie/update-categorie.component';
import { CategorieDetailsComponent } from './categorie-details/categorie-details.component';
import { ThemeListComponent } from './theme-list/theme-list.component';
import { UpdateThemeComponent } from './update-theme/update-theme.component';
import { ThemeComponent } from './theme/theme.component';
import { ThemeDetailsComponent } from './theme-details/theme-details.component';
// import { ThemeListComponent } from './theme-list/theme-list.component';


@NgModule({
  declarations: [
    AppComponent,
    // ThemeComponent,
    CategorieComponent,
    CategorieListComponent,
    UpdateCategorieComponent,
    CategorieDetailsComponent,
    ThemeListComponent,
    UpdateThemeComponent,
    ThemeComponent,
    ThemeDetailsComponent,
    // ThemeListComponent,
    
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,RouterModule,
    HttpClientModule,
    FormsModule,
    RouterModule,
   
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
