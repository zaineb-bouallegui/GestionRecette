import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { EventModel } from '../class/event-model';
import { DataService } from '../services/data.service';
@Component({
  selector: 'app-event-update',
  templateUrl: './event-update.component.html',
  styleUrls: ['./event-update.component.css']
})
export class EventUpdateComponent implements OnInit {

  event: EventModel = {
    id: 0,
    title: '',
    description: '',
    lieu: '',
    event_theme: '',
    Nbre_participant: 0,
    imageUrl: '',
    date_event: new Date()
  };

  eventId: number = 0;


  constructor(
    private dataservice: DataService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Retrieve the 'id' route parameter
    this.route.params.subscribe(params => {
      this.eventId = +params['id']; // Convert to a number
      // Now you can use this.eventId in your component
    });
  }



  onSubmit(eventForm: NgForm) {
    if (eventForm.valid) {
      const eventData: EventModel = eventForm.value as EventModel;
      console.log(this.eventId)
      console.log(eventData)
      this.dataservice.updateEvent(this.eventId, eventData).subscribe(data =>{
        console.log(data)
      }
      );
    }
  }
}
