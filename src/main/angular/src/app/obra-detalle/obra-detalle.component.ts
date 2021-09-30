import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Obra } from '../model/Obra';
import { ObraService } from '../service/obra.service';
import { Location } from '@angular/common';
import { ObraDeTipoDetalleComponent } from '../obra-de-tipo-detalle/obra-de-tipo-detalle.component';

@Component({
  selector: 'app-obra-detalle',
  templateUrl: './obra-detalle.component.html',
  styleUrls: ['./obra-detalle.component.css']
})
export class ObraDetalleComponent implements OnInit {

  @Input() obra: Obra;
  mensaje?: string;

  constructor(private obraService:ObraService,
    private router: ActivatedRoute, private location: Location) {

    this.obra = <Obra>{};
  }

  ngOnInit(): void {
    this.getObra();
  }

  getObra(): void {
    let id = Number(this.router.snapshot.paramMap.get("id"));

    if (id) {
      this.obraService.find(id)
        .subscribe(respuesta => this.obra = respuesta);
    }
  }

  save(): void {
    if (this.isEmpty(this.obra.nombre) && this.isEmpty(this.obra.autor) && this.obra.fecha !== null) {
     if (this.obra.fecha >= 0) {
      if (this.obra.id) {
        this.update();
        this.mensaje = 'Se ha modificado la obra';
        alert('Se ha modificado la obra ' + this.obra.nombre);
      }


      else {
        this.create();
        alert('Se ha creado la obra' + this.obra.nombre)
      }
     } else {
      alert("El año tiene que ser un número positivo");
     }
      
    }
    else {
      alert("El campo del nombre, autor/a y el año son obligatorios");
    }
  }

  create(): void {
      this.obraService.save(this.obra).subscribe(resultado => this.obra = resultado);
    }

  update(): void {
    this.obraService.update(this.obra).subscribe(resultado => this.obra = resultado);
  }

  volver(): void {
    this.location.back();
  }

  isEmpty(value: string): boolean{

    if(value == undefined || value ==null || value.length==0)
    {
      return false;
    }
    else{
      return true;
    }

  }
}

