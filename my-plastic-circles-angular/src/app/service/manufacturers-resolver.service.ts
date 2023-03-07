import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from "@angular/router";
import { Observable, of } from "rxjs";
import { catchError, map } from "rxjs/operators";
import { ManufacturersResolved } from "../model/manufacturer";
import { ManufacturersService } from "./manufacturers.service";

@Injectable({
  providedIn: 'root'
})
export class ManufacturersResolver implements Resolve<ManufacturersResolved> {

  constructor (private manufacturersService: ManufacturersService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ManufacturersResolved> {
    return this.manufacturersService.getManufacturers()
    .pipe( 
      map(manufacturers => ({manufacturers: manufacturers})),
      catchError(error => {
        const message = `Retrieval error: ${error}`;
        console.error(message);
        return of({manufacturers: null, error: message});
      })
    );
  }
}