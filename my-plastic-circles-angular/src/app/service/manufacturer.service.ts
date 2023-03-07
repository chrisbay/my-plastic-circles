import { HttpClient, HttpErrorResponse, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, throwError } from "rxjs";
import { catchError } from "rxjs/operators";
import { Manufacturer } from "../model/manufacturer";
import { BaseService } from "./base.service";

@Injectable({
  providedIn: 'root'
})
export class ManufacturerService extends BaseService {

  private manufacturersUrl: string = 'http://localhost:8081/api/manufacturer';

  constructor(private http: HttpClient) {
    super();
  }

  getManufacturers(): Observable<Manufacturer[]> {
    return this.http.get<Manufacturer[]>(this.manufacturersUrl)
    .pipe(
        catchError(this.handleError)
    );
  }

  save(manufacturer: Manufacturer): Observable<Manufacturer> {
    const httpOptions = {
      headers: new HttpHeaders({'Content-Type':  'application/json'})
    };
    return this.http.post<Manufacturer>(this.manufacturersUrl, manufacturer, httpOptions)
      .pipe(
        catchError(this.handleError)
      );
  }

}