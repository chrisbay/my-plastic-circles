import { HttpClient, HttpErrorResponse, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, throwError } from "rxjs";
import { catchError, map } from "rxjs/operators";
import { Manufacturer } from "./manufacturer";

@Injectable({
  providedIn: 'root'
})
export class ManufacturersService {

  private manufacturersUrl: string = 'http://localhost:8081/api/manufacturer';

  constructor(private http: HttpClient) {}

  getManufacturers(): Observable<Manufacturer[]> {
    return this.http.get<Manufacturer[]>(this.manufacturersUrl)
    .pipe(
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