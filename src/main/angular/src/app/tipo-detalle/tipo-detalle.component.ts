import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Tipo } from '../model/Tipo';
import { TipoService } from '../service/tipo.service';
import { Location } from '@angular/common';
import { ObraDeTipoDetalleComponent} from '../obra-de-tipo-detalle/obra-de-tipo-detalle.component';
import { ObraService } from '../service/obra.service';

@Component({
  selector: 'app-tipo-detalle',
  templateUrl: './tipo-detalle.component.html',
  styleUrls: ['./tipo-detalle.component.css']
})
export class TipoDetalleComponent implements OnInit {

  @Input() tipo: Tipo;
  id: number;
  
  constructor(private tipoService: TipoService,
    private router: ActivatedRoute, private location: Location) { 
      
      this.tipo = <Tipo>{};
      this.id=0;
    }

  ngOnInit(): void {
    this.getTipo();
    this.id = Number(this.router.snapshot.paramMap.get("id"));
  }

  getTipo(): void {
    let id = Number(this.router.snapshot.paramMap.get("id"));

      if(id) {
        this.tipoService.findById(id)
        .subscribe(respuesta => this.tipo = respuesta);
      }
    }

  save(): void{
    if (this.isEmpty(this.tipo.descripcion) && this.isEmpty(this.tipo.nombre)) {
    if(this.tipo.id)
    {
      this.update();
      alert('Se ha modificado el tipo de obra de arte ' + this.tipo.id);

    }
    else{
      this.create();
      alert('Se ha creado el tipo de obra de arte');
    }
  } else{
    alert("El campo de nombre y descripcion son obligatorios");
  }
}

    create(): void{
      this.tipoService.save(this.tipo).subscribe(resultado => this.tipo = resultado);
    }

    update() :void{
      this.tipoService.update(this.tipo).subscribe(resultado => this.tipo = resultado);
    }

    volver() :void{
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
