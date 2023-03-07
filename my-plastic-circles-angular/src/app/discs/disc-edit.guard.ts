import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { MessageType } from '../model/message';
import { MessageService } from '../service/message.service';

@Injectable({
  providedIn: 'root'
})
export class DiscEditGuard implements CanActivate {

  constructor(private router: Router,
              private messageService: MessageService) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      const idParam = next.paramMap.get('id');
      const id = Number(idParam);
    if (isNaN(id) || id < 1) {
      this.messageService.addMessage({
        type: MessageType.Error, 
        message: `Invalid disc id: ${idParam}`
      });
      this.router.navigate(['/discs']);
      return false;
    }
    
    return true;
  }
  
}
