import { HttpClient, HttpErrorResponse, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, throwError } from "rxjs";
import { catchError, map, tap } from "rxjs/operators";
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

}