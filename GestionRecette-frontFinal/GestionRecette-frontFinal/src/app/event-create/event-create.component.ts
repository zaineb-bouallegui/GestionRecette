import { NgForm } from '@angular/forms';
import { Component } from '@angular/core';
import { DataService } from '../services/data.service';
import { EventModel } from '../class/event-model';

@Component({
  selector: 'app-event-create',
  templateUrl: './event-create.component.html',
  styleUrls: ['./event-create.component.css']
})
export class EventCreateComponent {
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

  constructor(private dataservice: DataService) {}

  onSubmit(eventForm: NgForm) {
    if (eventForm.valid) {
      const eventData: EventModel = eventForm.value as EventModel;
      console.log(eventForm.value)
      this.dataservice.createEvent(eventData).subscribe((createdEvent) => {
        // Traitez la réponse du service (par exemple, affichez un message de succès)
        console.log('Événement créé :', createdEvent);
      });
    }
  }

}
