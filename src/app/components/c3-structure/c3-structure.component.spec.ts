import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { C3StructureComponent } from './c3-structure.component';

describe('C3StructureComponent', () => {
  let component: C3StructureComponent;
  let fixture: ComponentFixture<C3StructureComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ C3StructureComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(C3StructureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
