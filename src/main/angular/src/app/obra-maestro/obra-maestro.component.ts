import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Obra } from '../model/Obra';
import { ObraService } from '../service/obra.service';

@Component({
  selector: 'app-obra-maestro',
  templateUrl: './obra-maestro.component.html',
  styleUrls: ['./obra-maestro.component.css']
})
export class ObraMaestroComponent implements OnInit {

  obras: Obra[] = [];

  constructor(private obraService: ObraService,
    private location: Location) { }

  ngOnInit(): void {
    this.listObras();
  }

  listObras(): void {
    this.obraService.findall().subscribe(list => {this.obras = list;});
  }

  borrar(id: number): void {
    if(confirm("¿Estas seguro de que quieres borrar la obra?"))
    {
      this.obraService.delete(id).subscribe();
      window.location.reload();
    }
  }

  volver(): void {
    this.location.back();
  }

}

