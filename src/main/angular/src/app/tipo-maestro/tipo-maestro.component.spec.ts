import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TipoMaestroComponent } from './tipo-maestro.component';

describe('TipoMaestroComponent', () => {
  let component: TipoMaestroComponent;
  let fixture: ComponentFixture<TipoMaestroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TipoMaestroComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TipoMaestroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  xit('should create', () => {
    expect(component).toBeTruthy();
  });
});
