import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateRecetteComponent } from './update-recette.component';

describe('UpdateRecetteComponent', () => {
  let component: UpdateRecetteComponent;
  let fixture: ComponentFixture<UpdateRecetteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateRecetteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateRecetteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
