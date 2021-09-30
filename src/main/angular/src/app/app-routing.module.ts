import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ObraDeTipoDetalleComponent } from './obra-de-tipo-detalle/obra-de-tipo-detalle.component';
import { ObraDetalleComponent } from './obra-detalle/obra-detalle.component';
import { ObraMaestroComponent } from './obra-maestro/obra-maestro.component';
import { IndiceComponent } from './indice/indice.component';
import { TipoDetalleComponent } from './tipo-detalle/tipo-detalle.component';
import { TipoMaestroComponent } from './tipo-maestro/tipo-maestro.component';

const routes: Routes = [ 
  {path: 'index', component: IndiceComponent},
  {path: '', redirectTo: 'index', pathMatch: 'full'},
  {path: 'obra/list', component: ObraMaestroComponent},
  {path: 'obra', component: ObraDetalleComponent},
  {path: 'obra/:id', component: ObraDetalleComponent},
  {path: 'tipo/list', component: TipoMaestroComponent},
  {path: 'tipo', component: TipoDetalleComponent},
  {path: 'tipo/:id', component: TipoDetalleComponent},
  {path: 'obraTipo', component: ObraDeTipoDetalleComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
