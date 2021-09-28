import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { ObraDeTipo } from '../model/ObraDeTipo';
import { Obra } from '../model/Obra';

@Injectable({
  providedIn: 'root'
})
export class ObraDeTipoService {

  url = 'http://localhost:8080/api/obratipo';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  findAll(): Observable<ObraDeTipo[]> {
    return this.http.get<ObraDeTipo[]>(this.url);
  }

  save(obraTipo: ObraDeTipo): Observable<ObraDeTipo> {
    return this.http.post<ObraDeTipo>(this.url, obraTipo, this.httpOptions)
  }

  getObrasTipo(id:number): Observable<Obra[]>
  {
    const urlact='getObras';
    const xurl= `${this.url}/${urlact}/${id}`;
    console.log(xurl);
    return this.http.get<Obra[]>(xurl);
  }
}
