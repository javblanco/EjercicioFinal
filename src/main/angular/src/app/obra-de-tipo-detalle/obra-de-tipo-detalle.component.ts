import { Component, Input, OnInit, ChangeDetectorRef } from '@angular/core';
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import { Obra } from '../model/Obra';
import { ObraService } from '../service/obra.service';
import { ObraDeTipoService } from '../service/obra-de-tipo';
import { ObraDeTipo } from '../model/ObraDeTipo';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-obra-de-tipo-detalle',
  templateUrl: './obra-de-tipo-detalle.component.html',
  styleUrls: ['./obra-de-tipo-detalle.component.css']
})
export class ObraDeTipoDetalleComponent implements OnInit {

  obras: Obra[] = [];
  dropdownSettings = {};
  obrasSeleccionadas: Obra[] = [];
  obrasDelTipo: Obra[] = [];
  obrasYaSS: ObraDeTipo[] = [];
  @Input() obraDT: ObraDeTipo;

  constructor(private obraService: ObraService, private router: ActivatedRoute,
    private obraDeTipoService: ObraDeTipoService, private changeDetection: ChangeDetectorRef) {
    this.obraDT = <ObraDeTipo>{};
  }

  ngOnInit(): void {
    this.listObras();
    this.obrasSeleccionadas = [];
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'id',
      textField: 'nombre',
      value: 'id',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 3,
      allowSearchFilter: true
    };
    this.mostrar();
  }

  listObras(): void {
    this.obraService.findall().subscribe(list => { this.obras = list; });
  }

  volver(): void {

  }

  public save(): void {
    if(this.obrasSeleccionadas.length!=0)
    {
    this.obrasSeleccionadas.forEach(obra => {
      this.obraDT.idTipo = Number(this.router.snapshot.paramMap.get("id"));
      this.obraDT.idObra = obra["id"];
      this.obraDeTipoService.save(this.obraDT).subscribe(resultado => { this.obraDT = resultado; this.mostrar() });

    });
    }
    else{
      alert("No se ha seleccionado niguna obra");
    }
    this.obraDT = <ObraDeTipo>{};
  }
  
  public mostrar(): void {
    let id = Number(this.router.snapshot.paramMap.get("id"));
    this.obraDeTipoService.getObrasTipo(id).subscribe(resultado2 => { this.obrasDelTipo = resultado2;});
    this.changeDetection.detectChanges();
  }
}

