import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { C3CupComponent } from './c3-cup.component';

describe('C3CupComponent', () => {
  let component: C3CupComponent;
  let fixture: ComponentFixture<C3CupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ C3CupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(C3CupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
