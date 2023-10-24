// import { NgModule, APP_INITIALIZER } from '@angular/core';
// import { BrowserModule } from '@angular/platform-browser';
// // import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular'; // Comment out Keycloak imports
// import { AppRoutingModule } from './app-routing.module';
// import { AppComponent } from './app.component';

// /* Comment out Keycloak configuration and initialization
// const keycloakConfig = {
//   url: 'http://localhost:8080/auth',
//   realm: 'JobBoardKeycloack',
//   clientId: 'plat-service',
//   credentials: {
//     secret: '2613909a-57a7-4795-b9aa-23fa7b1d6fcc',
//     username: 'zaineb',
//     password: 'zaineb'
//   },
//   initOptions: {
//     onLoad: 'check-sso',
//     silentCheckSsoRedirectUri:
//       window.location.origin + '/assets/silent-check-sso.html'
//   }
// };
// */

// @NgModule({
//   declarations: [AppComponent],
//   imports: [AppRoutingModule, BrowserModule],
//   providers: [
//     /* Comment out Keycloak provider
//     {
//       provide: APP_INITIALIZER,
//       useFactory: (keycloak: KeycloakService) => {
//         return async () => {
//           await keycloak.init({ config: keycloakConfig });
//         };
//       },
//       multi: true,
//       deps: [KeycloakService]
//     }
//     */
//   ],
//   bootstrap: [AppComponent]
// })
// export class AppModule {}

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { KeycloakService } from '../keycloak.service';
@NgModule({
  declarations: [AppComponent],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, ReactiveFormsModule,FormsModule], // Add HttpClientModule here
  providers: [KeycloakService],
  bootstrap: [AppComponent]
})
export class AppModule { }