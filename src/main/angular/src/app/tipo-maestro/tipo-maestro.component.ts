import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Tipo } from '../model/Tipo';
import { TipoService } from '../service/tipo.service';

@Component({
  selector: 'app-tipo-maestro',
  templateUrl: './tipo-maestro.component.html',
  styleUrls: ['./tipo-maestro.component.css']
})
export class TipoMaestroComponent implements OnInit {


  tipos: Tipo[] = [];

  constructor(private tipoService: TipoService,
    private location: Location) { }

  ngOnInit(): void {
    this.listTipos();
  }

  listTipos(): void {
    this.tipoService.findAll().subscribe(list => {this.tipos = list;});
  }

  borrar(id: number): void {
    if(confirm("¿Estas seguro de que quieres borrar el estilo artístico?"))
    {
      this.tipoService.delete(id).subscribe();
      window.location.reload();
    }
  }

  volver(): void {
    this.location.back();
  }

}

