import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, of } from "rxjs";
import { catchError, map } from "rxjs/operators";
import { Disc } from "../model/disc";
import { BaseService } from "./base.service";

@Injectable({
  providedIn: 'root'
})
export class DiscService extends BaseService {

  private discsUrl: string = 'http://localhost:8081/api/disc';

  constructor(private http: HttpClient) {
    super();
  }

  getAll(): Observable<Disc[]> {
    return this.http.get<Disc[]>(this.discsUrl)
      .pipe(
        catchError(this.handleError)
    );
  }

  get(id: number): Observable<Disc> {
    if (id === 0) return of(this.initializeDisc());

    return this.http.get<Disc>(`${this.discsUrl}/${id}`)
      .pipe(
        catchError(this.handleError)
      );
  }

  save(disc: Disc): Observable<Disc> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };
    return this.http.post<Disc>(this.discsUrl, disc, httpOptions)
      .pipe(
        catchError(this.handleError)
      );
  }

  update(disc: Disc): Observable<Disc> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
      })
    };
    const requestUrl = `${this.discsUrl}/${disc.id}`;
    return this.http.put<Disc>(requestUrl, disc, httpOptions)
      .pipe(
        map(() => disc),
        catchError(this.handleError)
      );
  }

  delete(id: number): Observable<any> {
    const requestUrl = `${this.discsUrl}/${id}`;
    return this.http.delete(requestUrl)
      .pipe(
        catchError(this.handleError)
      );
  }

  private initializeDisc(): Disc {
    return {
      id: 0,
      model: '',
      manufacturer: null,
      speed: null,
      glide: null,
      turn: null,
      fade: null,
      notes: '',
      favorite: false
    };
  }

}