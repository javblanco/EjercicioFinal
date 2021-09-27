import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tipo } from '../model/Tipo';

@Injectable({
  providedIn: 'root'
})
export class TipoService {

  url = 'http://localhost:8080/api/tipo';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  findall(): Observable<Tipo[]> {
    return this.http.get<Tipo[]>(this.url);
  }

  find(id:number): Observable<Tipo> {
    const urlId = `${this.url}/${id}`;
    return this.http.get<Tipo>(urlId);
  }

  save(tipo: Tipo): Observable<Tipo> {
    return this.http.post<Tipo>(this.url, tipo, this.httpOptions);
  }

  update(tipo: Tipo): Observable<Tipo> {
    return this.http.put<Tipo>(this.url, tipo, this.httpOptions);
  }

  delete(id: number): Observable<any> {
    const urlId = `${this.url}/${id}`;
    return this.http.delete<Tipo>(urlId,this.httpOptions);
  }
}