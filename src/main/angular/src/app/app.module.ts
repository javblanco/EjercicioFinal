import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IndiceComponent } from './indice/indice.component';
import { ObraMaestroComponent } from './obra-maestro/obra-maestro.component';
import { ObraDetalleComponent } from './obra-detalle/obra-detalle.component';
import { TipoDetalleComponent } from './tipo-detalle/tipo-detalle.component';
import { TipoMaestroComponent } from './tipo-maestro/tipo-maestro.component';
import { ObraDeTipoDetalleComponent } from './obra-de-tipo-detalle/obra-de-tipo-detalle.component';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';

@NgModule({
  declarations: [
    AppComponent,
    IndiceComponent,
    ObraMaestroComponent,
    ObraDetalleComponent,
    TipoDetalleComponent,
    TipoMaestroComponent,
    ObraDeTipoDetalleComponent
  ],
  imports: [
    NgMultiSelectDropDownModule.forRoot(),
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule
  ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
