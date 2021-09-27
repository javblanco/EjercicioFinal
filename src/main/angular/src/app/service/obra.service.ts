import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Obra } from '../model/Obra';
@Injectable({
  providedIn: 'root'
})
export class ObraService {

  url = 'http://localhost:8080/api/obra';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  findall(): Observable<Obra[]> {
    return this.http.get<Obra[]>(this.url);
  }

  find(id:number): Observable<Obra> {
    const urlId = `${this.url}/${id}`;
    return this.http.get<Obra>(urlId);
  }

  save(obra: Obra): Observable<Obra> {
    return this.http.post<Obra>(this.url, obra, this.httpOptions);
  }

  update(obra: Obra): Observable<Obra> {
    return this.http.put<Obra>(this.url, obra, this.httpOptions);
  }

  delete(id: number): Observable<any> {
    const urlId = `${this.url}/${id}`;
    return this.http.delete<Obra>(urlId,this.httpOptions);
  }
}
