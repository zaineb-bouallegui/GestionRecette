import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {EventModel} from './EventModel ';



@Injectable({
  providedIn: 'root'
})
export class DataService {

  private apiUrl = 'http://localhost:8089/Event';
  
  constructor(private http: HttpClient) {}

  private getall="/GetAll"

  // Get Events - Read
  getEvents(): Observable<EventModel[]>{
    return this.http.get<EventModel[]>(this.apiUrl+'/GetAll')
  }

  createEvent(eventData: EventModel): Observable<EventModel> {
    return this.http.post<EventModel>(`${this.apiUrl}/ajouter`, eventData);
  }

  deleteEvent(id: number): Observable<EventModel> {
    const url = `${this.apiUrl}/delete/${id}`;
    return this.http.delete<EventModel>(url);
  }


    // Service de mise à jour d'un événement
    updateEvent(eventId: number, eventData: EventModel): Observable<EventModel> {
      console.log("eventid"+eventId)
      return this.http.put<EventModel>(`${this.apiUrl}/update/${eventId}`, eventData);
    }
  
    getEventById(eventId: number): Observable<EventModel> {
      const url = `${this.apiUrl}/Event/${eventId}`; // Adjust the URL to match your API endpoint
      return this.http.get<EventModel>(url);
    }
}


























