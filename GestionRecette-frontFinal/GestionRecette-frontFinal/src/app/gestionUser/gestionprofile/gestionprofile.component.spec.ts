import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionprofileComponent } from './gestionprofile.component';

describe('GestionprofileComponent', () => {
  let component: GestionprofileComponent;
  let fixture: ComponentFixture<GestionprofileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionprofileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionprofileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
