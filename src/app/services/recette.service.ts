import { Injectable } from '@angular/core';
import { Recette } from '../class/recette';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RecetteService {

  recetteList: Recette[]= [];
  


  constructor(private http : HttpClient) { }

  //URL du Backend
  url = "http://localhost:8086/ms/recette";
  endPoint_a="/getAll";

 getAllRecettes(){
  return this.http.get<Recette[]>(this.url+this.endPoint_a );
 }

 endPoint_b="/ajout";
 addRecette(r:Recette){
  return this.http.post<Recette[]>(this.url+this.endPoint_b,r);
 }

 endPoint_c="/delete";
 deleteRecette(id:Number){
  return this.http.delete(this.url+this.endPoint_c+'/'+id)
 }

 endPoint_d="/update";
 updateRecette(idr:Number,r:Recette){
  return this.http.put<Recette>(this.url+this.endPoint_d+'/'+idr,r)
 }

 endPoint_e="/getById";
 getRecetteById(id:Number){
  return this.http.get<Recette[]>(this.url+this.endPoint_e+'/'+id);
 }




}
