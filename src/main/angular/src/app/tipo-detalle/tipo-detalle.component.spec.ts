import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TipoDetalleComponent } from './tipo-detalle.component';

describe('TipoDetalleComponent', () => {
  let component: TipoDetalleComponent;
  let fixture: ComponentFixture<TipoDetalleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TipoDetalleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TipoDetalleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
