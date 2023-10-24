import { Component, OnInit } from '@angular/core';
import {DataService} from 'src/app/data.service'
import {Router} from '@angular/router'
import {EventModel} from 'src/app/EventModel '

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'frontMs';
  events : any[] =[] ;



  constructor(private service:DataService, private router:Router) {

  }

  ngOnInit(): void {
    this.service.getEvents().subscribe(data =>{
      this.events=data ;
    })
  }


  deleteEvent(eventId: number): void {
    this.service.deleteEvent(eventId).subscribe(() => {



      //  Mettez à jour le tableau en supprimant l'événement supprimé de la liste actuelle
      this.events = this.events.filter(event => event.id !== eventId);
    }, error => {
      // Gérez les erreurs ici en cas d'échec de la suppression
      console.error('Erreur lors de la suppression de l\'événement :', error);
    });
  }



}
