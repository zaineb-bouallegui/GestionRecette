import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { DatePipe } from '@angular/common';

import { KeycloakService } from '../keycloak.service';
import { RECETTES } from './recettes';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [DatePipe],
})
export class AppComponent implements OnInit {
  title = 'frontMs';
  private apiUrl = 'http://localhost:8082';
  plats: any[] = [];
  selectedRecette: any = null;
  platsSucre: any[] = [];
  platsSale: any[] = [];
  platForm: any = {
    nom: '',
    categorie: '',
    description: '',
  };
  selectedPlat: any;
  recettes = RECETTES;
  staticUserId = 2;
  selectedDate: Date = new Date();
  userPlanifications: any[] = [];
  recetteId: any;
  platId:any
  yourAuthToken='eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJIZkZza1hvbzZ6Vkltd3N4U3g3OE4yYWlKTU9XMzRXMXF1YlFxd00tNlNNIn0.eyJleHAiOjE2OTgxODQ2MjAsImlhdCI6MTY5ODE4NDMyMCwianRpIjoiYzc5MGRiNzYtZTBmMS00MjllLWJkNDctNTc2OTA4ZDI0MmI0IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvcmVhbG1zL0pvYkJvYXJkS2V5Y2xvYWNrIiwiYXVkIjpbInJlY2V0dGUtc2VydmljZSIsImluZ3JlZGllbnQtc2VydmljZSIsInRoZW1lLXNlcnZpY2UiLCJwcm9maWxlLXNlcnZpY2UiLCJhY2NvdW50Il0sInN1YiI6IjZmOGQ2OThiLWRhNWUtNGU3NC1iOWFmLWMzMWUwMDY3ZjFmNCIsInR5cCI6IkJlYXJlciIsImF6cCI6InBsYXQtc2VydmljZSIsInNlc3Npb25fc3RhdGUiOiJmMzNiNmNjOS1hODNjLTRlNmEtOTc5ZS1iZTE4NmZiNzM3OTciLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHA6Ly9sb2NhbGhvc3Q6ODA4OC8qIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIiwidXNlciJdfSwicmVzb3VyY2VfYWNjZXNzIjp7InJlY2V0dGUtc2VydmljZSI6eyJyb2xlcyI6WyJ1c2VyIl19LCJpbmdyZWRpZW50LXNlcnZpY2UiOnsicm9sZXMiOlsidXNlciJdfSwidGhlbWUtc2VydmljZSI6eyJyb2xlcyI6WyJ1c2VyIl19LCJwbGF0LXNlcnZpY2UiOnsicm9sZXMiOlsidXNlciJdfSwicHJvZmlsZS1zZXJ2aWNlIjp7InJvbGVzIjpbInVzZXIiXX0sImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIGVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsInByZWZlcnJlZF91c2VybmFtZSI6InphaW5lYiJ9.CeEHBqO_uuZoQhhyAdCyXlQP07lYUrp-_xfitLWLZ9YTRe-qiSbyDmV3uaiwjU_mufRSfv9v_BqaqTqz0ha1VzFBfHnkNV_IiHuqn5vnLpYc9NNWo28i15-BJVozE8Qsp5RwrEkrvgz6jM9QSFGmZvoNp44drJ16vhm_iWfrRFW7kK8MUj0XNOrJnnVxoxlpebLvnuEJFedQRCdwHlXQk34ovBefabdSQNKYiuXgcfP13OPhsKAV3ZpqJ9eoLjknptBC5mQY2UV-DZyjR6LEGpsIsClwqrFKRPAYXE-zM02EWn--CFKrNwvlVUxqwSYaYXZJ9XScW5sGoASC5dpMOQ'
    constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private keycloakService: KeycloakService,
    private datePipe: DatePipe
  ) {}

  ngOnInit() {
    this.getAllPlatsWithRecetteDetails();
    this.fetchUserPlanifications();
  }

  onSubmit() {
    this.createPlats(this.platForm);
  }

  openRecetteModal(plat: any) {
    this.selectedRecette = {
      recetteTitre: plat.recetteTitre || null,
      recetteDescription: plat.recetteDescription || null,
    };
  }

  closeRecetteModal() {
    this.selectedRecette = { recetteTitre: '', recetteDescription: '' };
  }

  getAllPlatsWithRecetteDetails() {
    this.http.get<any[]>(this.apiUrl + '/api/plats').subscribe((data) => {
      // Initialize arrays for 'Sucré' and 'Salé' plats
      const sucrePlats: any[] = [];
      const salePlats: any[] = [];
      const allPlats: any[] = []; // Create a new array for all plats
  
      // Iterate through the received data and categorize plats
      data.forEach((plat) => {
        if (plat.platCategorie === 1) {
          sucrePlats.push(plat);
        } else if (plat.platCategorie === 0) {
          salePlats.push(plat);
        }
        allPlats.push(plat); // Add plat to the allPlats array
      });
  
      // Assign the categorized plats to class properties
      this.platsSucre = sucrePlats;
      this.platsSale = salePlats;
      this.plats = allPlats; // Assign all plats to the plats property
    });
  }
  



  onTabChange(category: any) {
    if (category === 0) {
      this.platsSucre = [];
      // Fetch and populate this.platsSucre from your API
    } else if (category === 1) {
      this.platsSale = [];
      // Fetch and populate this.platsSale from your API
    }
  }

  createPlats(plats: any) {
    // Replace 'yourAuthToken' with your actual token
    const headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.yourAuthToken);

    this.http.post<any[]>(`${this.apiUrl}/api/plats/add`, plats, { headers }).subscribe(
      (createdPlats) => {
        console.log('Plats created:', createdPlats);
        window.location.reload();
      },
      (error) => {
        console.error('Error creating plats:', error);
        // Handle errors here
      }
    );
  }

  onPlanifyClick() {
    this.createPlanification();
  }

  createPlanification() {
    // Build your planification object here to match the Planification class
    const planification = {
      id: this.selectedPlat.id, // Assuming this is the plat ID
      userId: this.staticUserId,
      planDateTime: this.formatDate(this.selectedDate),
    };
  
    // Replace 'yourAuthToken' with the actual token
    const headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.yourAuthToken);
  
    this.http.post<string>(`${this.apiUrl}/api/planifications`, planification, { headers }).subscribe(
      (response) => {
        console.log('Planification created:', response);
        // Handle the response here
      },
      (error) => {
        console.error('Error creating planification:', error);
        // Handle errors here
      }
    );
    window.location.reload()
  }
  

  formatDate(date: Date): string | null {
    return this.datePipe.transform(date, 'yyyy-MM-dd\'T\'HH:mm:ss\'Z\'');
  }
  

  fetchUserPlanifications() {

    // Make an HTTP request to fetch the user's planifications
    this.http.get<any[]>(`${this.apiUrl}/api/planifications/userPlans?userId=${this.staticUserId}`)
      .subscribe((data) => {
        this.userPlanifications = data;
      });
  }

  deletePlanification(id: number) {
    const authToken = 'YOUR_AUTH_TOKEN'; // Replace with your actual auth token
    const apiUrl = `${this.apiUrl}/api/planifications/${id}`;
  
    const headers = new HttpHeaders().set('Authorization', 'Bearer ' + this.yourAuthToken);
  
    this.http.delete(apiUrl, { headers }).subscribe(
      (response) => {
        console.log('Planification deleted:', response);
        
      },
      (error) => {
        console.error('Error deleting planification:', error);
        // Handle errors here, e.g., show an error message
      }
      
    );
    window.location.reload();
  }
  

  assignRecetteToPlat(recetteId: number, platId: number) {
    const authToken = this.yourAuthToken; // Replace with your actual auth token
    const apiUrl = `${this.apiUrl}/api/plats/${platId}/assign-recette/${recetteId}`;
  
    const headers = new HttpHeaders().set('Authorization', 'Bearer ' + authToken);
  
    this.http.post(apiUrl, null, { headers }).subscribe(
      (response) => {
        console.log('Recette assigned to plat:', response);
        // Handle success, such as updating the UI
      },
      (error) => {
        console.error('Error assigning recette to plat:', error);
        // Handle errors here, e.g., show an error message
      }
    );
    window.location.reload()
  }

  deletePlat(platId: number) {
    // Replace 'yourAuthToken' with your actual token
    const authToken = this.yourAuthToken;
    console.log(platId)
  
    // Send an HTTP DELETE request to your server to delete the plat
    this.http.delete(`${this.apiUrl}/api/plats/${platId}`, {
      headers: new HttpHeaders().set('Authorization', 'Bearer ' + authToken),
    }).subscribe(
      (response) => {
        console.log('Plat deleted:', response);
        // Handle the response, e.g., remove the plat from your local list
        // and refresh the data if needed
      },
      (error) => {
        console.error('Error deleting plat:', error);
        // Handle errors here, e.g., show an error message
      }
    );
  }
  
  
}