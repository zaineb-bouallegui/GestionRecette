import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Profile } from '../class/profile';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private baseURL = "http://localhost:8091/api/profile/get";

  constructor(private httpClient: HttpClient) { } // Injectez le service HttpClient ici

  getProfileList(): Observable<Profile[]> { // Utilisez un tableau pour la liste des profils
    return this.httpClient.get<Profile[]>(this.baseURL);
  }

  createProfile(profile: Profile): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, profile);
  }
  getProfileById(id: number): Observable<Profile>{
    return this.httpClient.get<Profile>(`${this.baseURL}/${id}`);
  }
  updateProfile(id: number, profile: Profile): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}`, profile);
  }
  deleteProfile(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }

  
}
