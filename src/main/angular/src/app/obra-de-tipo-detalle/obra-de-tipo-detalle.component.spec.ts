import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObraDeTipoDetalleComponent } from './obra-de-tipo-detalle.component';

describe('ObraDeTipoDetalleComponent', () => {
  let component: ObraDeTipoDetalleComponent;
  let fixture: ComponentFixture<ObraDeTipoDetalleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ObraDeTipoDetalleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ObraDeTipoDetalleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  xit('should create', () => {
    expect(component).toBeTruthy();
  });
});
