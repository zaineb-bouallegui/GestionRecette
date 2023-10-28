import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Categorie } from '../class/categorie';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategorieService {

  private baseURL="http://localhost:8088/api/themes/categorie"
  constructor(private httpClient:HttpClient) {}
    
  getCategoriesList():Observable<Categorie[]>{
      return this.httpClient.get<Categorie[]>(`${this.baseURL}/get`);
    }


    createCategorie(categorie:Categorie):Observable<Object>{
      return this.httpClient.post(`${this.baseURL}`,categorie)
    }

    getCategorieById(id:number):Observable<Categorie>{
      return this.httpClient.get<Categorie>(`${this.baseURL}/get/${id}`);
    }

    updateCategorie(id: number, categorie: Categorie): Observable<Object>{
      return this.httpClient.put(`${this.baseURL}/${id}`, categorie);
    }
  deleteCategorie(id:number):Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/delete/${id}`);
  }
}
