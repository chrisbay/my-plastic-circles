import { HttpClient, HttpErrorResponse, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, of, throwError } from "rxjs";
import { catchError, map } from "rxjs/operators";
import { Disc } from "./disc";

@Injectable({
  providedIn: 'root'
})
export class DiscsService {

  private discsUrl: string = 'http://localhost:8081/api/disc';

  constructor(private http: HttpClient) {}

  getDiscs(): Observable<Disc[]> {
    return this.http.get<Disc[]>(this.discsUrl)
      .pipe(
        catchError(this.handleError)
    );
  }

  getDisc(id: number): Observable<Disc> {
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

  toggleFavoriteStatus(disc: Disc): Observable<Disc> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const url = `${this.discsUrl}/${disc.id}`;
    return this.http.put<Disc>(url, disc, { headers })
      .pipe(
        map(() => disc),
        catchError(this.handleError)
      );
  }

  private handleError(err: HttpErrorResponse): Observable<never> {
    let errorMessage: string;
    if (err.error instanceof ErrorEvent) {
      errorMessage = `An error occurred: ${err.error.message}`;
    } else {
      errorMessage = `Backend returned code ${err.status}: ${err.message}`;
    }
    console.error(err);
    return throwError(() => errorMessage);
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