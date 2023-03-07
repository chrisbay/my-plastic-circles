import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from "@angular/router";
import { Observable, of } from "rxjs";
import { catchError, map } from "rxjs/operators";
import { DiscResolved } from "../model/disc";
import { DiscsService } from "./discs.service";

@Injectable({
  providedIn: 'root'
})
export class DiscResolver implements Resolve<DiscResolved> {

  constructor (private discsService: DiscsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<DiscResolved> {
    const id = route.paramMap.get('id');
    if (isNaN(Number(id))){
      const message = `Disc id was not a number: ${id}`;
      console.error(message);
      return of({disc: null, error: message});
    }
    return this.discsService.getDisc(Number(id))
    .pipe( 
      map(disc => ({disc: disc})),
      catchError(error => {
        const message = `Retrieval error: ${error}`;
        console.error(message);
        return of({disc: null, error: message});
      })
    );
  }
}