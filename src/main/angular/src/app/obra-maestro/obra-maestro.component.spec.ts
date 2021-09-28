import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObraMaestroComponent } from './obra-maestro.component';

describe('ObraMaestroComponent', () => {
  let component: ObraMaestroComponent;
  let fixture: ComponentFixture<ObraMaestroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ObraMaestroComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ObraMaestroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  xit('should create', () => {
    expect(component).toBeTruthy();
  });
});
