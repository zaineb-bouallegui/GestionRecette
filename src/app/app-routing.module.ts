import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {EventCreateComponent} from './event-create/event-create.component'
import {EventUpdateComponent} from './event-update/event-update.component'

const routes: Routes = [
  { path: 'event-create', component: EventCreateComponent },
  { path: 'event-update/:id', component: EventUpdateComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
