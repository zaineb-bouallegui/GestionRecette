import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ingredient } from '../class/ingredient';

@Injectable({
  providedIn: 'root'
})
export class IngredientService {

  private baseURL = "http://localhost:8088/api/ingredients";

  constructor(private httpClient: HttpClient) { } 

  getIngredientList(): Observable<Ingredient[]> { 
    return this.httpClient.get<Ingredient[]>(this.baseURL);
  }

  createIngredient(ingredient: Ingredient): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, ingredient);
  }
  getIngredientById(id: number): Observable<Ingredient>{
    return this.httpClient.get<Ingredient>(`${this.baseURL}/${id}`);
  }
  updateIngredient(id: number, ingredient: Ingredient): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}`, ingredient);
  }
  deleteIngredient(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }
}
