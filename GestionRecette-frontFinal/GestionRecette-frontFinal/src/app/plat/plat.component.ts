import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { RECETTES } from '../class/plat';
import { RecetteService } from '../services/recette.service';
import { Recette } from '../class/recette';






@Component({
  selector: 'app-plat',
  templateUrl: './plat.component.html',
  styleUrls: ['./plat.component.css'],
  providers: [DatePipe],
})
export class PlatComponent implements OnInit {

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
  recetteList:Recette[]=[];
  selectedPlat: any;
  recettes = RECETTES;
  staticUserId = 2;
  selectedDate: Date = new Date();
  userPlanifications: any[] = [];
  recetteId: any;
  platId:any
  yourAuthToken='eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJIZkZza1hvbzZ6Vkltd3N4U3g3OE4yYWlKTU9XMzRXMXF1YlFxd00tNlNNIn0.eyJleHAiOjE2OTgyMzA2MDAsImlhdCI6MTY5ODIzMDMwMCwianRpIjoiODAzNWU0ODctNDIyNy00MWNjLThjNjEtNmU0NmNhZjY3OGRjIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvcmVhbG1zL0pvYkJvYXJkS2V5Y2xvYWNrIiwiYXVkIjpbInJlY2V0dGUtc2VydmljZSIsImluZ3JlZGllbnQtc2VydmljZSIsInRoZW1lLXNlcnZpY2UiLCJwcm9maWxlLXNlcnZpY2UiLCJhY2NvdW50Il0sInN1YiI6IjQ4YWNkMmRkLTcxZWQtNGNkMy04MTMwLWZiMDcyZjMxNTkwNyIsInR5cCI6IkJlYXJlciIsImF6cCI6InBsYXQtc2VydmljZSIsInNlc3Npb25fc3RhdGUiOiI1NTIwN2E2MS0zNWJjLTRmMjYtYTZmZS00ZTE3MjI1NzU5OTciLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHA6Ly9sb2NhbGhvc3Q6ODA4OC8qIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsImFkbWluIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJyZWNldHRlLXNlcnZpY2UiOnsicm9sZXMiOlsiYWRtaW4iXX0sImluZ3JlZGllbnQtc2VydmljZSI6eyJyb2xlcyI6WyJhZG1pbiJdfSwidGhlbWUtc2VydmljZSI6eyJyb2xlcyI6WyJhZG1pbiJdfSwicGxhdC1zZXJ2aWNlIjp7InJvbGVzIjpbImFkbWluIl19LCJwcm9maWxlLXNlcnZpY2UiOnsicm9sZXMiOlsiYWRtaW4iXX0sImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIGVtYWlsIHByb2ZpbGUiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJBeW1lbiBNem91Z2hpIiwicHJlZmVycmVkX3VzZXJuYW1lIjoiYXltZW4iLCJnaXZlbl9uYW1lIjoiQXltZW4iLCJmYW1pbHlfbmFtZSI6Ik16b3VnaGkiLCJlbWFpbCI6ImF5bWVuLm16b3VnaGlAZXNwcml0LnRuIn0.SBCbLCp22CCgCycM7U-9PGo7JWbj2kdLTrJnh42mevd0T5paOx197BZ3YBodCNPU9kzRXkVNTWfUeJOAE46353wjiPptC_F-5lsdQWOIpDoFOq8GmOkiJuREQM7enwQtsfgRvY9kGu1lJWV_POBNcdgnKxrETYu2Md9PDh255TYlS1Diw36ykf-ZAwfVI-JEuf_DAq4An4ZrPbNzln4exZQTevtedyg6AiRwXZAaepTiPJuwFq8nznxuNWdQXekSV6MPQ7agdSqqH6YNStqnXe6Q0ybwcDkKEOq4gtUZ82hZ0I6HmpWtoZ7m-mB8b_a92FE11FgzI7xXuHJgr6L2sA'
    
  
  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private recetteService:RecetteService,
    private datePipe: DatePipe
  ) {}

  ngOnInit() {
    this.getAllPlatsWithRecetteDetails();
    this.fetchUserPlanifications();
    this.getRecettes();
  }

  getTokenFromLocalStorage() {
    const token = localStorage.getItem('authToken');
    if (token) {
      // Split the token to get the payload part
      const tokenParts = token.split('.');
      
      // Check if the token has at least two parts (header and payload)
      if (tokenParts.length === 3) {
        try {
          // Decode the payload (second part)
          const payload = JSON.parse(atob(tokenParts[1]));
          
          // Extract the user ID
          const userId = payload.sub; // 'sub' typically represents the user ID in JWT
          console.log('User ID:', userId);
          return userId;
        } catch (error) {
          console.error('Error decoding token:', error);
          return null;
        }
      } else {
        console.error('Invalid token format');
        return null;
      }
    } else {
      console.error('Token not found in local storage');
      return null;
    }
  }
  
  
  onSubmit() {
    this.createPlats(this.platForm);
    const userId = this.getTokenFromLocalStorage();
    if (userId) {
      this.staticUserId= userId;
      console.log(this.staticUserId)
    }
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
    this.http.get<any[]>(`${this.apiUrl}/api/plats`).subscribe((data) => {
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


  getRecettes(){
    this.recetteService.getAllRecettes().subscribe(data =>{this.recetteList = data
      console.log(data)
    });
   
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
    window.location.reload();
  }
  
  

}
