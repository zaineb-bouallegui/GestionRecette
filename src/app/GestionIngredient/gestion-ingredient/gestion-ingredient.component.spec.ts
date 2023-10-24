import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionIngredientComponent } from './gestion-ingredientcomponent';

describe('GestionIngredientComponent', () => {
  let component: GestionIngredientComponent;
  let fixture: ComponentFixture<GestionIngredientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionIngredientComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionIngredientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
