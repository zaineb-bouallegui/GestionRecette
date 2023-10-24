import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventUpdateComponent } from './event-update.component';

describe('EventUpdateComponent', () => {
  let component: EventUpdateComponent;
  let fixture: ComponentFixture<EventUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EventUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EventUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
