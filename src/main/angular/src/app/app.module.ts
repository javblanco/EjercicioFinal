import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MaestroObraComponent } from './maestro-obra/maestro-obra.component';
import { MaestroTipoComponent } from './maestro-tipo/maestro-tipo.component';
import { TipoMaestroComponent } from './tipo-maestro/tipo-maestro.component';
import { ObraMaestroComponent } from './obra-maestro/obra-maestro.component';
import { ObraDetalleComponent } from './obra-detalle/obra-detalle.component';
import { TipoDetalleComponent } from './tipo-detalle/tipo-detalle.component';
import { IndiceComponent } from './indice/indice.component';
import { ModelComponent } from './model/model.component';
import { ServiceComponent } from './service/service.component';
import { ObraDeTipoDetalleComponent } from './obra-de-tipo-detalle/obra-de-tipo-detalle.component';
import { ObraDeTipoMaestroComponent } from './obra-de-tipo-maestro/obra-de-tipo-maestro.component';

@NgModule({
  declarations: [
    AppComponent,
    MaestroObraComponent,
    MaestroTipoComponent,
    TipoMaestroComponent,
    ObraMaestroComponent,
    ObraDetalleComponent,
    TipoDetalleComponent,
    IndiceComponent,
    ModelComponent,
    ServiceComponent,
    ObraDeTipoDetalleComponent,
    ObraDeTipoMaestroComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
